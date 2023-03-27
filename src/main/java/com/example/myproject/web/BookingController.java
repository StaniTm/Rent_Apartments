package com.example.libraryStore.web;

import com.example.libraryStore.domain.model.banding.business.BookingAvailabilityForm;
import com.example.libraryStore.service.business.BookingService;
import com.example.libraryStore.service.business.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/booking")
public class BookingController {
    private static final String BINDING_ADDRESS = "org.springframework.validation.BindingResult.";
    private final BookingService bookingService;
    private final OfferService offerService;

    @Autowired
    public BookingController(BookingService bookingService, OfferService offerService) {
        this.bookingService = bookingService;
        this.offerService = offerService;
    }

    //booking requests
    @PostMapping("/{offerId}")
    public String checkUserAvailability(@PathVariable("offerId") Long offerId,
                                        BookingAvailabilityForm form,
                                        Principal principal,
                                        BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("offerId", offerId);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BINDING_ADDRESS + "userAvailabilityForm", bindingResult).
                    addFlashAttribute("userAvailabilityForm", form);

            return "redirect:/booking/{offerId}";
        }

        boolean isAvailable = bookingService.createUserBooking(form, offerId, principal.getName());

        if (!isAvailable) {
            return "redirect:/booking/error/{offerId}";
        }

        return "redirect:/booking/thank&you/{offerId}";
    }

    @GetMapping("/error/{offerId}")
    public String getBookingErrorPage(@PathVariable("offerId") Long offerId, Model model) {
        model.addAttribute("offer", this.offerService.getViewById(offerId));

        return "booking-error";
    }

    @GetMapping("/{offerId}")
    public String getBookingPage(@PathVariable("offerId") Long offerId, Model model) {
        model.addAttribute("offer", this.offerService.getViewById(offerId));

        return "booking";
    }

    @GetMapping("/thank&you/{offerId}")
    public String getBookingThankYouPage(@PathVariable("offerId") Long offerId, Model model) {
        model.addAttribute("offer", this.offerService.getViewById(offerId));

        return "booking-thank-you";
    }

    @ModelAttribute(name = "bookingAvailabilityForm")
    public BookingAvailabilityForm checkAvailabilityForm() {
        return new BookingAvailabilityForm();
    }
}
