package com.example.weather.controller;

import com.example.weather.entity.City;
import com.example.weather.entity.User;
import com.example.weather.service.CityService;
import com.example.weather.service.RestService;
import com.example.weather.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


@Controller
public class MainController {
    final RestService restService;
    final UserService userService;
    final CityService cityService;

    public MainController(RestService restService, UserService userService, CityService cityService) {
        this.restService = restService;
        this.userService = userService;
        this.cityService = cityService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/list")
    public String cityList(@AuthenticationPrincipal User user, Map<String, Object> model) {
        List<City> cities = cityService.getCitiesByUser(user.getId());
        model.put("city", cities);

        return "list";
    }

    @GetMapping("/view")
    public String view(@RequestParam(name = "id") Long id) {
        return "view";
    }
}
