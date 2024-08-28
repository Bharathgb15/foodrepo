package com.food.foodmodule.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.foodmodule.controller.MenuItemController;
import com.food.foodmodule.entity.MenuItem;
import com.food.foodmodule.exception.DishNotFoundException;
import com.food.foodmodule.repositroy.MenuItemRepository;

@Service
public class MenuItemService {
	private static final Logger logger = LoggerFactory.getLogger(MenuItemService.class);

	@Autowired
	private MenuItemRepository repository;

	public MenuItem addMenu(MenuItem menu) {
		return repository.save(menu);

	}

	public MenuItem getMenuById(int id) throws DishNotFoundException {
        logger.info("Fetching menu item with ID: {}", id);
        Optional<MenuItem> menu = repository.findById(id);
        if (menu.isPresent()) {
            logger.info("Menu item found: {}", menu.get());
            return menu.get();
        } else {
            logger.error("Dish not found with ID: {}", id);
            throw new DishNotFoundException("Dish not found");
        }
    }


	 public List<MenuItem> getMenus(String dishTypename) throws DishNotFoundException {
	        logger.info("Fetching menu items for dish type: {}", dishTypename);
	        List<MenuItem> dishes = repository.findByDishType(dishTypename);
	        if (dishes.isEmpty()) {
	            logger.error("No dishes found in category: {}", dishTypename);
	            throw new DishNotFoundException("No dishes found in category " + dishTypename);
	        }
	        logger.info("Dishes found for category {}: {}", dishTypename, dishes);
	        return dishes;
	    }
}
