package com.food.foodmodule.repositroy;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.foodmodule.entity.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

	List<MenuItem> findByDishType(String dishTypename);
}
