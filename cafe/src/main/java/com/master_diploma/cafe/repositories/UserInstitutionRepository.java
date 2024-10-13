package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.UserInstitution;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInstitutionRepository extends CrudRepository<UserInstitution, Long> {
}
