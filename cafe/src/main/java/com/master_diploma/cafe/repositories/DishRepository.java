package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Dish;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {

    Set<Dish> findByInstitutionId(long institutionId);
    //TODO
    @Transactional
    @Query(value = "SELECT * FROM get_dish_of_the_day(:institutionId, :date)", nativeQuery = true)
    Dish findDishOfTheDay(long institutionId, @Param("date") LocalDate date);
}
