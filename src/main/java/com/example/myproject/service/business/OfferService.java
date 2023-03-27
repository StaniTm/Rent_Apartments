package com.example.myproject.service.business;

import com.example.myproject.domain.entity.ApartmentEntity;
import com.example.myproject.domain.entity.CityEntity;
import com.example.myproject.domain.entity.OfferEntity;
import com.example.myproject.domain.model.dtoS.business.OfferModel;
import com.example.myproject.domain.model.errors.OfferNotFoundException;
import com.example.myproject.domain.model.view.ImageViewModel;
import com.example.myproject.domain.model.view.OfferViewModel;
import com.example.myproject.domain.model.view.UserViewModel;
import com.example.myproject.repository.OfferRepository;
import com.example.myproject.service.initDB.InitDB;
import com.example.myproject.service.user.UserService;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferService implements InitDB {
    private final OfferRepository offerRepository;
    private final CityService cityService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public OfferService(OfferRepository offerRepository, CityService cityService, ModelMapper modelMapper, UserService userService) {
        this.offerRepository = offerRepository;
        this.cityService = cityService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    public List<OfferViewModel> getAllOfferViews() {
        return this.offerRepository.findAll().
                stream().
                map(offer -> this.modelMapper.map(offer, OfferViewModel.class)).
                toList();
    }

    public List<OfferViewModel> getOffersByRoomSize(Integer size) {
        List<OfferEntity> offerEntities = this.offerRepository.findAllByApartmentRoomCount(size).orElse(new ArrayList<>());

        return offerEntities.
                stream().
                map(offerEntity -> this.modelMapper.map(offerEntity, OfferViewModel.class).
                        setImages(offerEntity.getApartment().
                                getImages().
                                stream().
                                map(imageEntity ->  this.modelMapper.map(imageEntity, ImageViewModel.class)).
                                collect(Collectors.toList()))).
                collect(Collectors.toList());
    }

    public List<OfferViewModel> getOffersByCityAndRoomCount(String cityName, int size) {
        return getOffersByCity(cityName).
                stream().
                filter(offer -> offer.getApartment().getRoomCount() == size).
                toList();
    }

    public List<OfferViewModel> getOffersByCity(String cityName) {
        CityEntity cityEntity = this.cityService.getCityByName(cityName);
        List<OfferEntity> offerEntities = this.offerRepository.findAllByApartment_City(cityEntity).orElseThrow();

        return offerEntities.
                stream().
                map(offerEntity -> this.modelMapper.map(offerEntity, OfferViewModel.class).
                        setImages(offerEntity.getApartment().
                                getImages().
                                stream().
                                map(imageEntity ->  this.modelMapper.map(imageEntity, ImageViewModel.class)).
                                collect(Collectors.toList()))).
                collect(Collectors.toList());
    }

    public List<OfferViewModel> getCurrentUserOffers(String username) {
        UserViewModel user = this.userService.getViewModelByUsername(username);
        return this.offerRepository.findAllByApartmentOwnerId(user.getId()).
                orElseThrow().
                stream().
                map(offer -> this.modelMapper.map(offer, OfferViewModel.class)).
                toList();
    }

    public List<OfferViewModel> getLatestOffers() {
        return this.offerRepository.
                findAll().
                stream().
                sorted((o1, o2) -> o2.getCreatedOn().compareTo(o1.getCreatedOn())).
                limit(4).
                map(offerEntity -> this.modelMapper.map(offerEntity, OfferViewModel.class)).
                collect(Collectors.toList());
    }

    public OfferModel getModelById(Long id) {
        OfferEntity offerEntity = this.offerRepository.findById(id).orElse(null);
        if (offerEntity == null) throw new OfferNotFoundException("The offer you are looking for doesnt exist!");

        return this.modelMapper.map(offerEntity, OfferModel.class);
    }

    public OfferViewModel getViewById(Long id) {
        OfferEntity offerEntity = this.offerRepository.findById(id).orElse(null);
        if (offerEntity == null) throw new OfferNotFoundException("The offer you are looking for doesnt exist!");

        return this.modelMapper.map(offerEntity, OfferViewModel.class);
    }

    public OfferEntity getEntityById(Long id) {
        OfferEntity offerEntity = this.offerRepository.findById(id).orElse(null);
        if (offerEntity == null) throw new OfferNotFoundException("The offer you are looking for doesnt exist!");

        return offerEntity;
    }

    public OfferEntity createOffer(String minLength, Double price, ApartmentEntity apartment) {
        OfferEntity offerEntity = this.offerRepository.saveAndFlush(new OfferEntity().
                setMinLength(minLength.equals("one") ? 1 : 2).
                setPrice(price).
                setApartment(apartment).
                setCreatedOn(new java.sql.Date(new Date().getTime())));

        return offerEntity;
    }

    public void removeOffer(OfferModel offerModel) {
        OfferEntity offerEntity = this.offerRepository.findById(offerModel.getId()).orElse(null);
        if (offerEntity == null) throw new OfferNotFoundException("The offer you are looking for doesnt exist!");

        this.offerRepository.deleteById(offerModel.getId());
    }


    //creating few Offers and Apartments
    @Override
    public boolean isDbInit() {
        return this.offerRepository.count() == 0;
    }

    @Override
    @PostConstruct
    public void dbInit() {
        if (isDbInit()) {

        }
    }
}
