package com.master_diploma.cafe.services;

import com.master_diploma.cafe.models.Dish;
import com.master_diploma.cafe.repositories.DishRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DishOfTheDayService {
    private final DishRepository dishRepository;
    private Dish dishOfTheDay;
    private LocalDate lastUpdateDate;
    public Dish getDishOfTheDay() {
        if (dishOfTheDay == null || !lastUpdateDate.isEqual(LocalDate.now())) {
            dishOfTheDay = dishRepository.findRandomDish();
            lastUpdateDate = LocalDate.now();
        }
        return dishOfTheDay;
    }
}
