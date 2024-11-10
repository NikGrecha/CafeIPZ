package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.UserTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTableRepository extends CrudRepository<UserTable, Long> {
    UserTable findByFirstName(String firstName);
    Optional<UserTable> findByEmail(String email);

    @Transactional
    @Query(value = "SELECT * FROM user_table WHERE role_id != 3", nativeQuery = true)
    List<UserTable> findAllWorkers(Long roleId);

    @Transactional
    @Query(value = "SELECT * FROM user_table WHERE role_id = 2 ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    UserTable findAnyCook();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE user_table SET role_id = :newRoleId WHERE id = :id", nativeQuery = true)
    void updateRoleUser(@Param("newRoleId") Long roleId, @Param("id") Long id);
}
