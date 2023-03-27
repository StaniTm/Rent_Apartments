package com.example.libraryStore.web;

import com.example.libraryStore.domain.model.banding.business.ApartmentOfferForm;
import com.example.libraryStore.domain.model.view.CityViewModel;
import com.example.libraryStore.domain.model.view.OfferViewModel;
import com.example.libraryStore.service.business.ApartmentService;
import com.example.libraryStore.service.business.CityService;
import com.example.libraryStore.service.business.OfferService;
import com.example.libraryStore.utils.Utils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

//controls both apartment and offer
@Controller
@RequestMapping("/offers")
public class OfferController {
    private static final String BINDING_ADDRESS = "org.springframework.validation.BindingResult.";
    private final OfferService offerService;
    private final ApartmentService apartmentService;
    private final CityService cityService;
    private final Utils utils;

    @Autowired
    public OfferController(OfferService offerService, ApartmentService apartmentService, CityService cityService, Utils utils) {
        this.offerService = offerService;
        this.apartmentService = apartmentService;
        this.cityService = cityService;
        this.utils = utils;
    }

    //get All apartments/offers
    @GetMapping("/all")
    public String getAllOffers(Model model) {
        model.addAttribute("offers", this.offerService.getAllOfferViews());

        return "all-apartments";
    }

    //get all offers by rooms count no city filtration
    @GetMapping("/all/room/size/{size}")
    public String getOffersByRoomSize(@PathVariable("size") Integer size, Model model) {
        model.addAttribute("offers", this.offerService.getOffersByRoomSize(size));

        return "all-apartments";
    }

    //getMy apartments/offers
    @GetMapping("/user/apartments")
    public String getCurrentUserOffers(Model model, Principal principal) {
        model.addAttribute("offers", this.offerService.getCurrentUserOffers(principal.getName()));

        return "my-apartments";
    }

    @GetMapping("/{cityName}")
    public String getCityOffers(@PathVariable("cityName") String cityName, Model model) {
        //add city url for use in link
        model.addAttribute("cityUrl", cityName);

        CityViewModel cityViewModel = this.cityService.getCityView(cityName);
        //
        cityViewModel.setName(utils.capitalizeWord(cityViewModel.getName()));
        model.addAttribute("city", cityViewModel);

        List<OfferViewModel> offers = this.offerService.getOffersByCity(cityName);
        model.addAttribute("offers", offers);

        return "city-offers";
    }

    //get offers by city and room count
    @GetMapping("/{cityName}/room/size/{count}")
    public String getSunnyBeachOffersByRoomCount(@PathVariable("cityName") String cityName,
                                                 @PathVariable("count") Integer count,
                                                 Model model) {
        //add city url for use in link
        model.addAttribute("cityUrl", cityName);

        model.addAttribute("offers", this.offerService.getOffersByCityAndRoomCount(cityName, count));
        CityViewModel cityViewModel = this.cityService.getCityView(cityName);
        //
        cityViewModel.setName(utils.capitalizeWord(cityViewModel.getName()));
        model.addAttribute("city", cityViewModel);

        return "city-offers";
    }

    //Add Offer
    @GetMapping("/add-offer")
    public String getAddOffer() {
        return "add-apartment";
    }

    @PostMapping("/add-offer")
    public String addOffer(@Valid @ModelAttribute("apartmentOfferForm") ApartmentOfferForm apartmentOfferForm,
                           @RequestParam("picture") MultipartFile multipartFile,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Principal principal) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BINDING_ADDRESS + "apartmentOfferForm", bindingResult)
                    .addFlashAttribute("apartmentOfferForm", apartmentOfferForm);

            return "redirect:/offers/add-apartment";
        }

        this.apartmentService.addApartment(apartmentOfferForm, multipartFile, principal.getName());

        return "redirect:/home";
    }

    //remove offer
    @GetMapping("/remove/{apartmentId}")
    public String removeOfferAndApartment(@PathVariable("apartmentId") Long apartmentId) {
        this.apartmentService.removeApartmentAndOffer(this.offerService.getModelById(apartmentId).getApartment().getId());

        return "redirect:/home";
    }

    //admin remove apartment and offer
    @GetMapping("/admin/remove/apartment/{apartmentId}")
    public String adminRemoveOfferAndApartment(@PathVariable("apartmentId") Long offerId) {
        this.apartmentService.removeApartmentAndOffer(this.offerService.getModelById(offerId).getApartment().getId());

        return "redirect:/admin/all-apartments";
    }

    //get selected offer
    @GetMapping("/selected/{offerId}")
    public String getSelectedOffer(@PathVariable("offerId") Long offerId, Model model, Principal principal) {
        OfferViewModel offerViewModel = this.offerService.getViewById(offerId);
        model.addAttribute("offer", offerViewModel).
                addAttribute("ownerUsername", offerViewModel.getApartment().getOwner().getUsername());
       if (principal != null) {
           model.addAttribute("principalUsername", principal.getName());
       } else {
           model.addAttribute("principalUsername", "none");
       }
        return "offer";
    }

    @ModelAttribute(name = "apartmentOfferForm")
    public ApartmentOfferForm apartmentOfferForm() {
        return new ApartmentOfferForm();
    }
}
