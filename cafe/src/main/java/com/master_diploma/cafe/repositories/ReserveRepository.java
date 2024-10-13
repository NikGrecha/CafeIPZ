package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Reserve;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends CrudRepository<Reserve, Long> {
}
