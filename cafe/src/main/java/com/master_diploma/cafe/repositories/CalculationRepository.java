package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Calculation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends CrudRepository<Calculation, Long> {
}