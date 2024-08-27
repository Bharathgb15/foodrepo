package com.food.foodmodule.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.foodmodule.entity.MenuItem;
import com.food.foodmodule.exception.DishNotFoundException;
import com.food.foodmodule.repositroy.MenuItemRepository;

@Service
public class MenuItemService {

	@Autowired
	private MenuItemRepository repository;

	public MenuItem addMenu(MenuItem menu) {
		return repository.save(menu);

	}

	public MenuItem getMenuById(int id) throws DishNotFoundException {
		Optional<MenuItem> menu = repository.findById(id);
		if (menu.isPresent()) {
			return menu.get();
		} else {
			throw new DishNotFoundException("Dish not found");
		}
	}


	public List<MenuItem> getMenus(String dishTypename) throws DishNotFoundException {
		List<MenuItem> dishes = repository.findByDishType(dishTypename);
		if (dishes.isEmpty()) {
			throw new DishNotFoundException("No dishes found in category " + dishTypename);
		}
		return dishes;
	}
}
