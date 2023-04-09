package com.example.myproject.web;

import com.example.myproject.service.business.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping
public class HomeController {
    private final OfferService offerService;

    @Autowired
    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("latestOffers", this.offerService.getLatestOffers());
        return "home";
    }
}
