package com.mycompany.property_management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class CalculatorDTO {
    private Double num1;
    private Double num2;
    @JsonProperty("num31")
    private Double num3;
}
