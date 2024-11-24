package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.dto.WorkerInstitutionDTO;
import com.master_diploma.cafe.models.UserTable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTableRepository extends CrudRepository<UserTable, Long> {
    UserTable findByFirstName(String firstName);
    Optional<UserTable> findByEmail(String email);

    @Query(value = """
            SELECT ut.* FROM user_institution ui
            JOIN user_table ut ON ui.user_worker_id = ut.id
            WHERE role_id != 3 AND role_id != 4 AND ui.institution_id = :institutionId""", nativeQuery = true)
    List<UserTable> findAllWorkersByInstitutionId(Long institutionId);

    @Query(value = "SELECT * FROM user_table WHERE role_id = 2 ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    UserTable findAnyCook();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE user_table SET role_id = :newRoleId WHERE id = :id", nativeQuery = true)
    void updateRoleUser(@Param("newRoleId") Long roleId, @Param("id") Long id);

    @Query(value = """
            SELECT new com.master_diploma.cafe.dto.WorkerInstitutionDTO(ut.id, ui.id, ut.firstName, ut.lastName) FROM UserInstitution ui
            JOIN ui.user ut
            WHERE (ut.role.id = 1 OR ut.role.id = 2) AND ui.institution.id = :institutionId""")
    List<WorkerInstitutionDTO> findAllWorkerInstitutionByInstitutionId(Long institutionId);
}
