package com.mycompany.property_management.converter;

import org.springframework.stereotype.Component;

import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;

@Component
public class PropertyConverter {

    public PropertyEntity convertDToToEntity(PropertyDTO propertyDTO) {

        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setTitle(propertyDTO.getTitle());
        propertyEntity.setDescription(propertyDTO.getDescription());
        propertyEntity.setAddress(propertyDTO.getAddress());
        propertyEntity.setPrice(propertyDTO.getPrice());

        return propertyEntity;

    }

    public PropertyDTO convertEntityToDTO(PropertyEntity propertyEntity) {

        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(propertyEntity.getId());
        propertyDTO.setTitle(propertyEntity.getTitle());
        propertyDTO.setDescription(propertyEntity.getDescription());
        propertyDTO.setAddress(propertyEntity.getAddress());
        propertyDTO.setPrice(propertyEntity.getPrice());

        return propertyDTO;

    }

}
