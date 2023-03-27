package com.example.myproject.service.business;


import com.example.myproject.domain.entity.ContractEntity;
import com.example.myproject.domain.model.banding.business.RentForm;
import com.example.myproject.domain.model.dtoS.business.ContractModel;
import com.example.myproject.domain.model.dtoS.business.OfferModel;
import com.example.myproject.domain.model.dtoS.user.UserModel;
import com.example.myproject.repository.ContractRepository;
import com.example.myproject.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.ZoneId;
import java.util.List;

@Service
public class ContractService {
    private final ContractRepository contractRepository;
    private final OfferService offerService;
    private final UserService userService;
    private final ApartmentService apartmentService;
    private final ModelMapper modelMapper;

    @Autowired
    public ContractService(ContractRepository contractRepository, OfferService offerService, UserService userService, ApartmentService apartmentService, ModelMapper modelMapper) {
        this.contractRepository = contractRepository;
        this.offerService = offerService;
        this.userService = userService;
        this.apartmentService = apartmentService;
        this.modelMapper = modelMapper;
    }


    public UserModel createContract(Long offerId, String username, RentForm form) {
        OfferModel offerModel = this.offerService.getModelById(offerId);
        //update the tenant/user info(phone, fullName, email)
        UserModel userModel = this.userService.updateUser(form, username);

        ContractModel contractModel = createModel(offerModel, userModel, form);
        contractModel.
                setMoveOutDate(contractModel.getMoveInDate().plusYears(1)).
                setRentPerMonth(offerModel.getPrice());

        ContractEntity contractEntity = this.modelMapper.map(contractModel, ContractEntity.class);
        contractEntity.
                setMoveInDate(Date.valueOf(contractModel.getMoveInDate())).
                setMoveOutDate(Date.valueOf(contractModel.getMoveOutDate()));

        //this.apartmentService.removeOffer(this.modelMapper.map(offerModel, OfferEntity.class));
        this.apartmentService.setIsListed(offerModel.getApartment());
        this.apartmentService.setIsRented(offerModel.getApartment());
        this.offerService.removeOffer(offerModel);
        this.contractRepository.saveAndFlush(contractEntity);

        return userModel;
    }

    public List<ContractModel> getAllContractModels() {
        return this.contractRepository.findAll().
                stream().
                map(entity -> this.modelMapper.map(entity, ContractModel.class).
                        setMoveInDate(entity.getMoveInDate().toLocalDate()).
                        setMoveOutDate(entity.getMoveOutDate().toLocalDate())).
                toList();
    }

    private ContractModel createModel(OfferModel offerModel, UserModel userModel, RentForm form) {
        return new ContractModel().
                setLength(offerModel.getMinLength()).
                setApartment(offerModel.getApartment()).
                setLength(offerModel.getMinLength()).
                setTenant(userModel).
                setMoveInDate(form.getMoveInDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
}
