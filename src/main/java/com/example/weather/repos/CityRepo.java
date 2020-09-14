package com.example.weather.repos;

import com.example.weather.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<City, Long> {
    List<City> findCitiesByUserId(Long id);

    City findByCityAndUserId(String name, Long id);
}