package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag, Long> {
}
