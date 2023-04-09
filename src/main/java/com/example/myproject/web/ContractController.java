package com.example.myproject.web;


import com.example.myproject.domain.model.banding.business.RentForm;
import com.example.myproject.domain.model.dtoS.user.UserModel;
import com.example.myproject.service.business.ContractService;
import com.example.myproject.service.business.OfferService;
import com.example.myproject.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/rent")
public class ContractController {
    private static final String BINDING_ADDRESS = "org.springframework.validation.BindingResult.";
    private final OfferService offerService;
    private final UserService userService;
    private final ContractService contractService;

    @Autowired
    public ContractController(OfferService offerService, UserService userService, ContractService contractService) {
        this.offerService = offerService;
        this.userService = userService;
        this.contractService = contractService;
    }

    //rent requests
    @GetMapping("/{offerId}")
    public String getRentPage(@PathVariable("offerId") Long offerId,
                              Model model,
                              Principal principal) {
        model.addAttribute("offer", this.offerService.getViewById(offerId))
                .addAttribute("user", this.userService.getViewModelByUsername(principal.getName()));

        return "rent";
    }

    @PostMapping("/{offerId}")
    public String postRent(@PathVariable("offerId") Long offerId,
                           @Valid @ModelAttribute(name = "rentForm") RentForm form,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model,
                           Principal principal) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BINDING_ADDRESS + "rentForm", bindingResult)
                    .addFlashAttribute("rentForm", form)
                    .addFlashAttribute("offerId", offerId);

            return "redirect:/rent/{offerId}";
        }

        UserModel userModel = this.contractService.createContract(offerId, principal.getName(), form);
        redirectAttributes.addFlashAttribute("user", userModel);

        return "redirect:/rent/thank&you";
    }

    @GetMapping("/thank&you")
    public String getRentThankYouPage(Model model, Principal principal) {
        model.addAttribute("user", this.userService.getViewModelByUsername(principal.getName()));
        return "rent-thank-you";
    }

    @ModelAttribute(name = "rentForm")
    public RentForm rentForm() {
        return new RentForm();
    }
}
