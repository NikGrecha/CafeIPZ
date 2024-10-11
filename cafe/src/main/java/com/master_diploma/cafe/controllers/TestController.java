package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Institution;
import com.master_diploma.cafe.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class TestController {
    @Autowired
    private InstitutionRepository institutionRepository;

    @GetMapping("/institutions")
    public Iterable<Institution> test(Model model){
        //        model.addAttribute("institutions", institutions);
        return institutionRepository.findAll();
    }

    @RequestMapping(value = "/institutions/save", method = RequestMethod.POST)
    public String insertInstitution(@RequestBody Institution institution) {
        institutionRepository.save(institution);
        return "Saved";
    }

}
