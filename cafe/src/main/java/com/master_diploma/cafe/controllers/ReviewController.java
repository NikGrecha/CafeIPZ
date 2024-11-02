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

import java.util.Optional;

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
        model.addAttribute("user", myUserDetailsService.getCurrentUserId());
        model.addAttribute("institution", institutionService.getInstitutionById(institutionId));
        model.addAttribute("reviews", reviewService.findByInstitutionId(institutionId));
        return "add-review";
    }
    @PostMapping("/reviews/add")
    public String addReview(@ModelAttribute("review") Review review, Model model) {

        Optional<Institution> institution = institutionRepository.findById(review.getInstitution().getId());
        institution.ifPresent(review::setInstitution);

        reviewService.saveReview(review);
        return "redirect:/api/v1/apps/institutions";
    }
}
