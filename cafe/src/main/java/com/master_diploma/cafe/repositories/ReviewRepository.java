package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Review;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    @Transactional
    @Query(value = "SELECT * FROM review WHERE institution_id = :id", nativeQuery = true)
    List<Review> findReviewByInstitutionId(@Param("id") Long institutionId);
}
