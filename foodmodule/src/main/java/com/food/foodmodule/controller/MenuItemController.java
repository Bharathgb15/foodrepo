package com.food.foodmodule.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

	@Autowired
    private MenuItemService service;


	 @PostMapping("/dish")
	    public ResponseEntity<MenuItem> addDish(@RequestBody MenuItem menu) {
	     MenuItem newMenu = service.addMenu(menu);
			return new ResponseEntity<>(newMenu, HttpStatus.CREATED);
	 }
	
	 @GetMapping("/dish/{dishid}")
	    public ResponseEntity<MenuItem> getDishById(@PathVariable int dishid) throws DishNotFoundException {
	        try {
	            MenuItem menu = service.getMenuById(dishid);
	            return new ResponseEntity<>(menu, HttpStatus.OK);
	        } catch (DishNotFoundException e) {
	            throw new DishNotFoundException("Dish not found ");
	        }
	    }
    
	@GetMapping("/category/{dishTypename}")
    public ResponseEntity<List<MenuItem>> getMenusBycategory(@PathVariable String dishTypename) throws DishNotFoundException {
        List<MenuItem> menus = service.getMenus(dishTypename);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }
	
	
}

