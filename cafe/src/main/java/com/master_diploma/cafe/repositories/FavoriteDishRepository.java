package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.FavoriteDish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteDishRepository extends CrudRepository<FavoriteDish, Long> {
}
