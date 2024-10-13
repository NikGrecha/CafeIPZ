package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Desk;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeskRepository extends CrudRepository<Desk, Long> {
}
