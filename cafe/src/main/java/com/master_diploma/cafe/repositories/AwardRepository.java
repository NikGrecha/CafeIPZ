package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Award;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardRepository extends CrudRepository<Award, Long> {
}
