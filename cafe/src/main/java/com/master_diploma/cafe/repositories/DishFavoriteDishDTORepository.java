package com.master_diploma.cafe.repositories;

import com.master_diploma.cafe.models.Dish;
import com.master_diploma.cafe.views.DishFavoriteDishView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishFavoriteDishDTORepository extends JpaRepository<Dish, Long> {

    @Query(nativeQuery = true, value = """
    WITH ranked_dishes AS (
        SELECT *,
               ROW_NUMBER() OVER (PARTITION BY title ORDER BY CASE WHEN user_client_id = :userClientId THEN 0 ELSE 1 END, id) AS rn
        FROM dish_favorite_dish_view
        WHERE institution_id = :institutionId
    )
    SELECT id, title, descriptions, price, institution_id, user_client_id
    FROM ranked_dishes
    WHERE rn = 1
    """)
    List<DishFavoriteDishView> findByInstitutionId(long institutionId, long userClientId);

}