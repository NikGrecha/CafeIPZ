package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Privilege;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
}
