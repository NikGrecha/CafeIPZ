package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {

    Set<Dish> findByInstitutionId(long institutionId);
}
