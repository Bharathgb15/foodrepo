package com.food.foodmodule.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "menu_item")
	public class MenuItem {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer dishId;
	    private String dishType;
	    private String dishName;
	    private Integer unitPrice;
	    private String dishDesc;

	   
	}
