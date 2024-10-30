package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Dish;
import com.master_diploma.cafe.repositories.DishRepository;
import com.master_diploma.cafe.repositories.InstitutionRepository;
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
    private InstitutionRepository institutionRepository;

    @GetMapping("/view")
    public String  findAllDishes(Model model){
        model.addAttribute("dishes", dishRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        return "all-dishes";
    }

    @PostMapping("/save")
    public String saveDish(@ModelAttribute Dish dish){
        dishRepository.save(dish);
        return "redirect:/api/v1/apps/dishes/view";
    }
}
