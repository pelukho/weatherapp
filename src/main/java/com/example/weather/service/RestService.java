package com.example.weather.service;

import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String getPostsPlainJSON(String url) {
        return this.restTemplate.getForObject(url, String.class);
    }

    public String getCurrentCity() {
        String location = getPostsPlainJSON("http://geolocation-db.com/json/");
        JSONObject root = new JSONObject(location);

       return root.getString("city");
    }

    public JSONObject getWeatherByCity(String city) {
        String location = getPostsPlainJSON("https://api.weatherbit.io/v2.0/forecast/daily?city="+ city +"&units=M&days=7&key=b6418d4a5dda4759972db425a61b583d");

        if (null == location) {
            return new JSONObject();
        }

        return new JSONObject(location);
    }
}
