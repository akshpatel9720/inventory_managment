package com.inventory.exception.adviser;

import com.inventory.dto.ResponseDto;
import com.inventory.exception.CategoryNotFoundException;
import com.inventory.exception.InventoryNotFoundException;
import com.inventory.exception.ShopNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviser {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private ResponseDto handle(CategoryNotFoundException exception) {
        return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Category not found", null);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private ResponseDto handle(ShopNotFoundException exception) {
        return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Shop not found", null);
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    private ResponseDto handle(InventoryNotFoundException exception) {
        return new ResponseDto(HttpStatus.BAD_REQUEST.value(), "Inventory not found", null);
    }

}
