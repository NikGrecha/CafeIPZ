package com.master_diploma.cafe.services;

import com.master_diploma.cafe.models.Institution;
import com.master_diploma.cafe.models.Review;
import com.master_diploma.cafe.repositories.InstitutionRepository;
import com.master_diploma.cafe.repositories.ReviewRepository;
import com.master_diploma.cafe.repositories.UserTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private InstitutionRepository institutionRepository;
    @Autowired
    private UserTableRepository userTableRepository;
    private static final Logger logger = LoggerFactory.getLogger(InstitutionService.class);
    public void saveReview(Review review){
        Institution institution = review.getInstitution();
        review.setDateOfCreation(LocalDateTime.now());
        logger.info("Сохраняется Institution: {}", institution);
        reviewRepository.save(review);
        // Пересчет среднего рейтинга заведения
        if(institution != null){
            updateInstitutionRating(institution);
        }
    }
    private void updateInstitutionRating(Institution institution){
        Set<Review> reviews = institution.getReviews();
        double totalStars = reviews.stream().mapToDouble(r ->
                (r.getStarsFood() + r.getStarsService() + r.getStarsAtmosphere()) / 3).sum();
        double averageRating = totalStars / reviews.size();
        institution.setNumberOfStars(averageRating);
        institutionRepository.save(institution);
    }
}
