package com.master_diploma.cafe.services;

import com.master_diploma.cafe.models.Dish;
import com.master_diploma.cafe.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class DishService {
    private DishRepository dishRepository;

    public List<Dish> findAll(){
        return (List<Dish>) dishRepository.findAll();
    }
    public Dish findById(long id){
        return findById(id);
    }
    public Dish findDishOfTheDay(long institutionId, LocalDate date){
        return dishRepository.findDishOfTheDay(institutionId, date);
    }
    public List<Dish> findByOrderId(long orderId){
        return dishRepository.findByOrderId(orderId);
    }
    public Set<Dish> findByInstitutionId(long institutionId){
        return dishRepository.findByInstitutionId(institutionId);
    }

    public void save(Dish dish){
        dishRepository.save(dish);
    }
    public void delete(Dish dish){
        dishRepository.delete(dish);
    }
}
