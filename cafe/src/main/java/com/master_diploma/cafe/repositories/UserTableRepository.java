package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.UserTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTableRepository extends CrudRepository<UserTable, Long> {
    UserTable findByFirstName(String firstName);
}
