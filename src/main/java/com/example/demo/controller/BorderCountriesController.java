package com.example.demo.controller;


import com.example.demo.dto.CountryInfo;
import com.example.demo.service.CountryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BorderCountriesController {
    @Autowired
    public CountryInfoService countryInfoService;

    @GetMapping(value = "/{countryCode}")
    public List<CountryInfo> getBorderCountries(@PathVariable String countryCode) {
        return countryInfoService.getCountryInfo(countryCode).getBorders();
    }
}
