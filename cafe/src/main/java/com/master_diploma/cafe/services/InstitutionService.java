package com.master_diploma.cafe.services;

import com.master_diploma.cafe.models.Institution;
import com.master_diploma.cafe.repositories.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstitutionService {
    @Autowired
    private InstitutionRepository institutionRepository;

    public Institution getInstitutionById(Long id) {
        return institutionRepository.findById(id).orElseThrow(() -> new RuntimeException("Institution not found"));
    }
}