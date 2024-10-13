package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
}
