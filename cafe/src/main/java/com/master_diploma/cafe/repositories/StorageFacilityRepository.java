package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.StorageFacility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageFacilityRepository extends CrudRepository<StorageFacility, Long> {
}
