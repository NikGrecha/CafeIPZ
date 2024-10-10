package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Institution;
import com.master_diploma.cafe.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @Autowired
    private InstitutionRepository institutionRepository;
    @GetMapping("/")
    public String test(Model model){
        Iterable<Institution> institutions = institutionRepository.findAll();
        model.addAttribute("institutions", institutions);
        return "index";
    }
}
