package com.example.weather.controller;

import com.example.weather.service.RestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomRestController {
    final RestService restService;
    public CustomRestController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/current")
    public String getWeather() {
        String city = restService.getCurrentCity();

        return restService.getWeatherByCity(city).toString();
    }

    @GetMapping("/by-city")
    public String getWeatherByCity(@RequestParam("city") String city) {
        return restService.getWeatherByCity(city).toString();
    }
}
