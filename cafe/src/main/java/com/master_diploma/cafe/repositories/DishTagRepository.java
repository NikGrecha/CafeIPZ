package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.DishTag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishTagRepository extends CrudRepository<DishTag, Long> {
}
