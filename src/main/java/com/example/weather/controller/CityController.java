package com.example.weather.controller;

import com.example.weather.entity.City;
import com.example.weather.entity.User;
import com.example.weather.service.CityService;
import com.example.weather.service.RestService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CityController {
    final private CityService cityService;
    final RestService restService;

    public CityController(CityService cityService, RestService restService) {
        this.cityService = cityService;
        this.restService = restService;
    }

    @GetMapping("/add")
    public String addCity(@RequestParam("city") String cityName, @AuthenticationPrincipal User user) {

        String getCity = restService.getWeatherByCity(cityName).toString();
        City cityFromDb = cityService.findByName(cityName, user.getId());

        if (getCity.equals("{}")) {
            return getCity;
        }

        if (cityFromDb != null) {
            return "{\"error\":\"City already exist!\"}";
        }

        City city = new City();
        city.setCity(cityName);
        city.setUser(user);
        cityService.saveCity(city);

        return city.toString();
    }

    @GetMapping("/delete")
    public String addCity(@RequestParam("id") Long id) {
        String deleted = cityService.findById(id).toString();
        cityService.delete(id);

        return deleted;
    }

    @GetMapping("/get-view")
    public String viewById(@RequestParam(name = "id") Long id) {
        City city = cityService.findById(id);

        return restService.getWeatherByCity(city.getCity()).toString();
    }
}
