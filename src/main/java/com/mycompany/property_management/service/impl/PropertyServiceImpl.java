package com.mycompany.property_management.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.property_management.converter.PropertyConverter;
import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.exception.ErrorModel;
import com.mycompany.property_management.repository.PropertyRepository;
import com.mycompany.property_management.repository.UserRepository;
import com.mycompany.property_management.service.PropertyService;

@Service // Can make a class singleton
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;
    @Autowired
    private UserRepository userRepository;

    @Override
    public PropertyDTO savProperty(PropertyDTO propertyDTO) {

        Optional<UserEntity> userData = userRepository.findById(propertyDTO.getUserId());

        if (userData.isPresent()) {

            PropertyEntity propertyEntity = propertyConverter.convertDToToEntity(propertyDTO);
            propertyEntity.setUserEntity(userData.get());
            propertyEntity = propertyRepository.save(propertyEntity);
            propertyDTO = propertyConverter.convertEntityToDTO(propertyEntity);

        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("USER_ID_NOT_EXIST");
            errorModel.setMessage("User does not exist");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }

        return propertyDTO;
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

    @Override
    public List<PropertyDTO> getPropertiesForUserId(Long userId) {
        List<PropertyDTO> propList = new ArrayList<>();

        List<PropertyEntity> listOfPropertyEntity = propertyRepository.findAllByUserEntityId(userId);

        for (PropertyEntity entity : listOfPropertyEntity) {
            PropertyDTO dto = propertyConverter.convertEntityToDTO(entity);
            propList.add(dto);
        }

        return propList;
    }

}
