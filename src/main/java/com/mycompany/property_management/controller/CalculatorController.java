package com.mycompany.property_management.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.property_management.dto.CalculatorDTO;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculatorController {

    // http://localhost:8080/api/v1/calculator/add?num1=2.0&num2=3.0
    // Method level mapping of url to a controller method
    @GetMapping("/add")
    public Double add(@RequestParam("num1") Double num1, @RequestParam("num2") Double num2) {
        return num1 + num2;
    }

    // map the values to url to java variables by Path Variable method
    // http://localhost:8080/api/v1/calculator/sub/5.0/4.0
    @GetMapping("/sub/{num1}/{num2}")
    public Double subtract(@PathVariable("num1") Double num1, @PathVariable("num2") Double num2) {
        Double result = null;
        if (num1 > num2) {
            result = num1 - num2;
        } else {
            result = num2 - num1;
        }
        return result;
    }

    // Path variable and Request param can be combined
    // http://localhost:8080/api/v1/calculator/divide/9?num1=6
    @GetMapping("/divide/{num2}")
    public Double divide(@RequestParam("num1") Double num1, @PathVariable("num2") Double num2) {
        Double result = null;
        if (num2 == 0) {
            result = 0.0;
        } else {
            result = num1 / num2;
        }
        return result;
    }

    /*
     * http://localhost:8080/api/v1/calculator/multiply
     * 
     * "num1":3.0,
     * "num2":4.0
     * 
     */
    @PostMapping("/multiply")
    public ResponseEntity<Double> multiply(@RequestBody CalculatorDTO calculatorDTO) {
        Double result = null;
        result = calculatorDTO.getNum1() * calculatorDTO.getNum2() * calculatorDTO.getNum3();
        return new ResponseEntity<>(result, HttpStatus.CREATED); // Can send two
    }
}
