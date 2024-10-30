package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.StorageFacility;
import com.master_diploma.cafe.repositories.StorageFacilityRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/apps/storages")
@AllArgsConstructor
@NoArgsConstructor
public class StorageFacilityController {

    @Autowired
    private StorageFacilityRepository storageFacilityRepository;

    @GetMapping("/view")
    public String storageFacilityFindAll(Model model){
        model.addAttribute("storages", storageFacilityRepository.findAll());
        return "all-storages";
    }

    @PostMapping("/save")
    public String storageFacilitySave(@ModelAttribute StorageFacility storageFacility){
        storageFacilityRepository.save(storageFacility);
        return "redirect:/api/v1/apps/storages/view";
    }
}
