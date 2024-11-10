package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Dish;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {

    Set<Dish> findByInstitutionId(long institutionId);

    //TODO
//    @Transactional
//    @Query(value = "SELECT * FROM dish WHERE institution_id == :institutionId", nativeQuery = true)
//    Dish findDishOfTheDay(long institutionId, LocalDate date);
}
