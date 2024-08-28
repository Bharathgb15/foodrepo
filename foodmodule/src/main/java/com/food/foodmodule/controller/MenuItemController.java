package com.food.foodmodule.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.food.foodmodule.entity.MenuItem;
import com.food.foodmodule.exception.DishNotFoundException;
import com.food.foodmodule.service.MenuItemService;


@RestController
public class MenuItemController {

	
	private static final Logger logger = LoggerFactory.getLogger(MenuItemController.class);
	@Autowired
    private MenuItemService service;


	 @PostMapping("/dish")
	    public ResponseEntity<MenuItem> addDish(@RequestBody MenuItem menu) {
		 logger.info("===================================");
		 logger.info("Request sent to add dish: {}", menu);
	     MenuItem newMenu = service.addMenu(menu);
	     logger.info("Added dish successfully: {}", menu);
			return new ResponseEntity<>(newMenu, HttpStatus.CREATED);
	 }
	
	 @GetMapping("/dish/{dishid}")
	    public ResponseEntity<MenuItem> getDishById(@PathVariable int dishid) throws DishNotFoundException {
	        try {
	        	logger.info("===================================");
	            MenuItem menu = service.getMenuById(dishid);
	            
	            return new ResponseEntity<>(menu, HttpStatus.OK);
	        } catch (DishNotFoundException e) {
	        	logger.info("===================================");
	        	logger.info("Dish retrive successfully: {}",dishid );
	            throw new DishNotFoundException("Dish not found ");
	        }
	    }
    
	@GetMapping("/category/{dishTypename}")
    public ResponseEntity<List<MenuItem>> getMenusBycategory(@PathVariable String dishTypename) throws DishNotFoundException {
		logger.info("===================================");
        List<MenuItem> menus = service.getMenus(dishTypename);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }
	
	
}

