package com.food.foodmodule.exception;


import lombok.Data;


@Data
public class ErrorMessage {

    private String msg;
    private int code;

    public ErrorMessage(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    
    
}