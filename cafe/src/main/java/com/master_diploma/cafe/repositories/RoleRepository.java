package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);
}
