package com.example.weather.service;

import com.example.weather.entity.City;
import com.example.weather.repos.CityRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepo cityRepo;

    public CityService(CityRepo cityRepo) {
        this.cityRepo = cityRepo;
    }

    public City findById(Long id) {
        return cityRepo.getOne(id);
    }

    public City findByName(String name, Long id){
        return cityRepo.findByCityAndUserId(name, id);
    }

    public List<City> getCitiesByUser(Long userId) {
        return cityRepo.findCitiesByUserId(userId);
    }

    public void saveCity(City city) {
        cityRepo.save(city);
    }

    public void delete(Long id) {
        cityRepo.deleteById(id);
    }
}