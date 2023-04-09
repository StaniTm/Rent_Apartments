package com.example.myproject.web;


import com.example.myproject.domain.model.banding.user.UserRegisterFormModel;
import com.example.myproject.service.business.ApartmentService;
import com.example.myproject.service.business.ContractService;
import com.example.myproject.service.business.OfferService;
import com.example.myproject.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping
public class UserController {
    private static final String BINDING_ADDRESS = "org.springframework.validation.BindingResult.";

    private final UserService userService;
    private final OfferService offerService;
    private final ApartmentService apartmentService;
    private final ContractService contractService;


    @Autowired
    public UserController(UserService userService, OfferService offerService,
                          ApartmentService apartmentService, ContractService contractService) {
        this.userService = userService;
        this.offerService = offerService;
        this.apartmentService = apartmentService;
        this.contractService = contractService;
    }


    @GetMapping("/user/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/user/login-error")
    public String onFailedLogin(
            @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/user/login";
    }


    @GetMapping("/user/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/user/register")
    public String postRegister(@Valid @ModelAttribute(name = "userRegisterFormModel") UserRegisterFormModel registerFormModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterFormModel", registerFormModel)
                    .addFlashAttribute(BINDING_ADDRESS + "userRegisterFormModel", bindingResult);

            return "redirect:register";
        }

        if (this.userService.checkUsernameAvailability(registerFormModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegisterFormModel", registerFormModel)
                    .addFlashAttribute("takenUsername", true);

            return "redirect:register";
        }

        this.userService.register(registerFormModel);

        return "redirect:/user/login";
    }

    //-->Admin Requests
    @GetMapping("/admin")
    public String getAdmin() {
        return "admin";
    }

    @GetMapping("/admin/all-apartments")
    public String getAllApartments(Model model) {
        model.addAttribute("apartments", this.apartmentService.getAllApartments());

        return "admin-all-apartments";
    }

    @GetMapping("/admin/all-offers")
    public String getAllOffers(Model model) {
        model.addAttribute("offers", this.offerService.getAllOfferViews());

        return "admin-all-offers";
    }

    @GetMapping("/admin/all-users")
    public String getAllUsers(Model model, Principal principal) {
        model.addAttribute("users", this.userService.getAllUserViews(principal.getName()));

        return "admin-all-users";
    }

    @GetMapping("/admin/change/user/role/{userId}")
    public String addUserRole(@PathVariable("userId") Long userId) {

        this.userService.addUserRole(userId);

        return "redirect:/admin/all-users";
    }

    //removes User with all his apartments/offers
    @GetMapping("/admin/delete/user/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId) {
        this.apartmentService.removeAllApartmentsAndOffersByOwnerId(userId);
        this.userService.deleteUser(userId);

        return "redirect:/admin/all-users";
    }

    @GetMapping("/admin/all-contracts")
    public String getALlContractsPage(Model model) {
        model.addAttribute("contracts", this.contractService.getAllContractModels());

        return "admin-all-contracts";
    }


    @ModelAttribute(name = "userRegisterFormModel")
    public UserRegisterFormModel userRegisterFormModel() {
        return new UserRegisterFormModel();
    }

    @ModelAttribute(name = "takenUsername")
    public boolean takenUsername() {
        return false;
    }
}
