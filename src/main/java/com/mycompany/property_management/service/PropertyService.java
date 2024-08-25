package com.mycompany.property_management.service;

import com.mycompany.property_management.dto.PropertyDTO;
import java.util.*;

public interface PropertyService {

    PropertyDTO savProperty(PropertyDTO propertyDTO);

    List<PropertyDTO> getAllProperties();

    PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId);

    PropertyDTO updateDescriptionOfProperty(PropertyDTO propertyDTO, Long propertyId);

    PropertyDTO updatePriceOfProperty(PropertyDTO propertyDTO, Long propertyId);

    void deleteProperty(Long propertyId);
}
