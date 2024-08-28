package com.mycompany.property_management.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyDTO {

    @Id
    private Long id;
    private String title;
    private String description;
    private Double price;
    private String address;
    private Long userId;

}