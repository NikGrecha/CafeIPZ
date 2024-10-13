package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Institution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends CrudRepository<Institution, Long> {
}
