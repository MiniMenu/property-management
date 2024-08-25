package com.mycompany.property_management.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.property_management.converter.PropertyConverter;
import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import com.mycompany.property_management.repository.PropertyRepository;
import com.mycompany.property_management.service.PropertyService;

@Service // Can make a class singleton
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO savProperty(PropertyDTO propertyDTO) {

        PropertyEntity propertyEntity = propertyConverter.convertDToToEntity(propertyDTO);
        propertyEntity = propertyRepository.save(propertyEntity);
        return propertyConverter.convertEntityToDTO(propertyEntity);
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyDTO> propList = new ArrayList<>();

        List<PropertyEntity> listOfPropertyEntity = (List<PropertyEntity>) propertyRepository.findAll();

        for (PropertyEntity entity : listOfPropertyEntity) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(entity);
            propList.add(dto);
        }

        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyRecord = propertyRepository.findById(propertyId);

        PropertyDTO dto = null;

        if (propertyRecord.isPresent()) {

            PropertyEntity entity = propertyRecord.get();
            entity.setTitle(propertyDTO.getTitle());
            entity.setDescription(propertyDTO.getDescription());
            entity.setAddress(propertyDTO.getAddress());
            entity.setOwnerName(propertyDTO.getOwnerName());
            entity.setOwnerEmail(propertyDTO.getOwnerEmail());
            entity.setPrice(propertyDTO.getPrice());
            propertyRepository.save(entity);

            dto = propertyConverter.convertEntityToDTO(entity);

        }
        return dto;
    }

    @Override
    public PropertyDTO updateDescriptionOfProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyRecord = propertyRepository.findById(propertyId);

        PropertyDTO dto = null;
        if (propertyRecord.isPresent()) {
            PropertyEntity entity = propertyRecord.get();

            entity.setDescription(propertyDTO.getDescription());

            propertyRepository.save(entity);
            dto = propertyConverter.convertEntityToDTO(entity);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePriceOfProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> propertyRecord = propertyRepository.findById(propertyId);
        
        PropertyDTO dto = null;
        if (propertyRecord.isPresent()) {
            PropertyEntity entity = propertyRecord.get();

            entity.setPrice(propertyDTO.getPrice());

            propertyRepository.save(entity);

            dto = propertyConverter.convertEntityToDTO(entity);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);       
    }

}
