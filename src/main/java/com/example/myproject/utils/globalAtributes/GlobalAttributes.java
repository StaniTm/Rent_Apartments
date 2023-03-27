package com.example.myproject.utils.globalAtributes;


import com.example.myproject.domain.model.view.CityViewModel;
import com.example.myproject.service.business.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class GlobalAttributes {

    private final CityService cityService;

    @Autowired
    public GlobalAttributes(CityService cityService) {
        this.cityService = cityService;
    }

    @ModelAttribute(name = "cityList")
    public List<CityViewModel> allCity() {
        return this.cityService.getAllCityViews();
    }
}
