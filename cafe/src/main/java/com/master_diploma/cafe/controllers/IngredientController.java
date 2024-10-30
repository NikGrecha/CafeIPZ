package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Ingredient;
import com.master_diploma.cafe.repositories.IngredientRepository;
import com.master_diploma.cafe.repositories.StorageFacilityRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/apps/ingredients")
@AllArgsConstructor
@NoArgsConstructor
public class IngredientController {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private StorageFacilityRepository storageFacilityRepository;

    @GetMapping("/view")
    public String findAll(Model model){
        model.addAttribute("ingredients", ingredientRepository.findAll());
        model.addAttribute("storageFacilities", storageFacilityRepository.findAll());
        return "all-ingredients";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Ingredient ingredient){
        ingredientRepository.save(ingredient);
        return "redirect:/api/v1/apps/ingredients/view";
    }


}
