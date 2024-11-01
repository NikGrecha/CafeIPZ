package com.master_diploma.cafe.controllers;

import com.master_diploma.cafe.models.Institution;
import com.master_diploma.cafe.models.Review;
import com.master_diploma.cafe.repositories.InstitutionRepository;
import com.master_diploma.cafe.repositories.ReviewRepository;
import com.master_diploma.cafe.services.InstitutionService;
import com.master_diploma.cafe.services.MyUserDetailsService;
import com.master_diploma.cafe.services.ReviewService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/apps")
@AllArgsConstructor
@NoArgsConstructor
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @GetMapping("/add/{institutionId}")
    public String showReviewForm(@PathVariable("institutionId") Long institutionId, Model model) {
        Institution institution = institutionService.getInstitutionById(institutionId);
        Review review = new Review();
        review.setInstitution(institution);
        model.addAttribute("user", myUserDetailsService.getCurrentUserId());
        model.addAttribute("institution", institution);
        model.addAttribute("review", review);
        return "add-review";
    }
    @PostMapping("/reviews/add")
    public String addReview(@ModelAttribute("review") Review review, Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        reviewService.saveReview(review);
        return "redirect:/api/v1/apps/institutions";
    }
}
