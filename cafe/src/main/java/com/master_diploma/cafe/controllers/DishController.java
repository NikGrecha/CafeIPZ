package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Dish;
import com.master_diploma.cafe.models.FavoriteDish;
import com.master_diploma.cafe.repositories.DishRepository;
import com.master_diploma.cafe.repositories.FavoriteDishRepository;
import com.master_diploma.cafe.repositories.InstitutionRepository;
import com.master_diploma.cafe.repositories.UserTableRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/apps/dishes")
@AllArgsConstructor
@NoArgsConstructor
public class DishController {

    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private UserTableRepository userTableRepository;
    @Autowired
    private FavoriteDishRepository favoriteDishRepository;
    @Autowired
    private InstitutionRepository institutionRepository;

    @GetMapping("/view")
    public String findAllDishes(Model model){
        model.addAttribute("dishes", dishRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "all-dishes";
    }

    @PostMapping("/save")
    public String saveDish(@ModelAttribute Dish dish){
        dishRepository.save(dish);
        return "redirect:/api/v1/apps/dishes/view";
    }

    @PostMapping("/addFavorite/{institutionId}/{dishId}/{userId}")
    public String saveFavoriteDish(@PathVariable Long institutionId, @PathVariable Long dishId, @PathVariable Long userId){
        FavoriteDish favoriteDish = new FavoriteDish();
        dishRepository.findById(dishId).ifPresent(favoriteDish::setDish);
        userTableRepository.findById(userId).ifPresent(favoriteDish::setUser);
        favoriteDishRepository.save(favoriteDish);
        return "redirect:/api/v1/apps/menu/" + institutionId;
    }
}
