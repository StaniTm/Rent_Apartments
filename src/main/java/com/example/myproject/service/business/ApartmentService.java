package com.example.myproject.service.business;


import com.example.myproject.domain.entity.ApartmentEntity;
import com.example.myproject.domain.model.banding.business.ApartmentOfferForm;
import com.example.myproject.domain.model.dtoS.business.ApartmentModel;
import com.example.myproject.domain.model.errors.ApartmentNotFoundException;
import com.example.myproject.repository.ApartmentRepository;
import com.example.myproject.service.initDB.InitDB;
import com.example.myproject.service.user.UserService;
import com.example.myproject.utils.Utils;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ApartmentService implements InitDB {
    private final String UPLOAD_DIRECTORY = "C:\\Users\\Admin\\Desktop\\SpringFundamentals\\libraryStore\\src\\main\\resources\\static\\images";
    private final ApartmentRepository apartmentRepository;
    private final CityService cityService;
    private final UserService userService;
    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final ImageService imageService;
    private final Utils utils;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository, CityService cityService, UserService userService, OfferService offerService, ModelMapper modelMapper, ImageService imageService, Utils utils) {
        this.apartmentRepository = apartmentRepository;
        this.cityService = cityService;
        this.userService = userService;
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.imageService = imageService;
        this.utils = utils;
    }

    public void addApartment(ApartmentOfferForm apartmentFormModel, MultipartFile multipartFile, String username) throws IOException {
        //get hotel image Filename and adding to ApartmentOfferForm
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        apartmentFormModel.setImage(fileName);

        //ApartmentOfferForm mapped to ApartmentModel
        ApartmentModel apartmentModel = this.modelMapper.map(apartmentFormModel, ApartmentModel.class);

        //ApartmentModel mapped to ApartmentEntity
        ApartmentEntity apartmentEntity = this.modelMapper.map(apartmentModel, ApartmentEntity.class).
                setCity(this.cityService.getCityByName(apartmentModel.getCityName())).
                setOwner(this.userService.getEntityByUsername(username)).
                setListed(true).
                setRented(false).
                setBooked(false);

        //create image
        apartmentEntity.setImages(List.of(this.imageService.createImage(fileName)));
        this.apartmentRepository.saveAndFlush(apartmentEntity);

        this.offerService.createOffer(apartmentFormModel.getMinLength(), apartmentModel.getPrice(), apartmentEntity);
        this.utils.saveFile(UPLOAD_DIRECTORY, fileName, multipartFile);
    }

    public List<ApartmentModel> getAllApartments() {
        return this.apartmentRepository.
                findAll().
                stream().
                map(entity -> this.modelMapper.map(entity, ApartmentModel.class)).
                toList();
    }

    public void setIsListed(ApartmentModel apartmentModel) {
        ApartmentEntity apartmentEntity = this.apartmentRepository.findById(apartmentModel.getId()).orElse(null);

        if (apartmentEntity == null) throw new ApartmentNotFoundException("Apartment was not found!");

        if (apartmentEntity.isListed()) {
            apartmentEntity.setListed(false);
        } else {
            apartmentEntity.setListed(true);
        }

        this.apartmentRepository.saveAndFlush(apartmentEntity);
    }

    public void setIsRented(ApartmentModel apartmentModel) {
        ApartmentEntity apartmentEntity = this.apartmentRepository.findById(apartmentModel.getId()).orElse(null);

        if (apartmentEntity == null) throw new ApartmentNotFoundException("Apartment was not found!");

        if (apartmentEntity.isRented()) {
            apartmentEntity.setRented(false);
        } else {
            apartmentEntity.setRented(true);
        }

        this.apartmentRepository.saveAndFlush(apartmentEntity);
    }

    public void setIsBooked(ApartmentModel apartmentModel) {
        ApartmentEntity apartmentEntity = this.apartmentRepository.findById(apartmentModel.getId()).orElse(null);

        if (apartmentEntity == null) throw new ApartmentNotFoundException("Apartment was not found!");

        if (apartmentEntity.isBooked()) {
            apartmentEntity.setBooked(false);
        } else {
            apartmentEntity.setBooked(true);
        }

        this.apartmentRepository.saveAndFlush(apartmentEntity);
    }

    public ApartmentEntity getEntityById(Long id) {
        ApartmentEntity apartmentEntity = this.apartmentRepository.findById(id).orElse(null);
        if (apartmentEntity == null) throw new ApartmentNotFoundException("Apartment was not found!");

        return apartmentEntity;
    }

    public ApartmentModel getModelById(Long id) {
        ApartmentEntity apartmentEntity = this.apartmentRepository.findById(id).orElse(null);
        if (apartmentEntity == null) throw new ApartmentNotFoundException("Apartment was not found!");

        return this.modelMapper.map(apartmentEntity, ApartmentModel.class);
    }

    public void removeApartmentAndOffer(Long apartmentId) {
        ApartmentEntity apartmentEntity = this.apartmentRepository.findById(apartmentId).orElse(null);
        if (apartmentEntity == null) throw new ApartmentNotFoundException("Apartment was not found!");

        this.apartmentRepository.deleteById(apartmentId);
    }

    @Transactional
    public void removeAllApartmentsAndOffersByOwnerId(Long ownerId) {
        Optional<List<ApartmentEntity>> allByOwnerId = this.apartmentRepository.findAllByOwnerId(ownerId);
        if (allByOwnerId.isEmpty()) throw new ApartmentNotFoundException("Apartment was not found!");

        this.apartmentRepository.deleteAllByOwnerId(ownerId);
    }

    @Override
    public boolean isDbInit() {
        return this.apartmentRepository.count() == 0;
    }

    @Override
    public void dbInit() {
        if (isDbInit()) {

        }
    }

    private void createApartment() {

    }
}
