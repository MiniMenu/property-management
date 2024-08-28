package com.mycompany.property_management.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.property_management.converter.UserConverter;
import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.entity.AddressEntity;
import com.mycompany.property_management.entity.UserEntity;
import com.mycompany.property_management.exception.BusinessException;
import com.mycompany.property_management.exception.ErrorModel;
import com.mycompany.property_management.repository.AddressRepository;
import com.mycompany.property_management.repository.UserRepository;
import com.mycompany.property_management.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public UserDTO register(UserDTO userDTO) {
        Optional<UserEntity> userExist = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        if (userExist.isPresent()) {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("EMAIL_ALREADY_EXISR");
            errorModel.setMessage("The email with which you are trying to register already exist");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);

        }

        UserEntity userEntity = userConverter.convertDTOToEntity(userDTO);
        userEntity = userRepository.save(userEntity);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setHouseNo(userDTO.getHouseNo());
        addressEntity.setCity(userDTO.getCity());
        addressEntity.setPostalCode(userDTO.getPostalCode());
        addressEntity.setStreet(userDTO.getStreet());
        addressEntity.setCountry(userDTO.getCountry());

        addressEntity.setUserEntity(userEntity);
        addressRepository.save(addressEntity);
        return userConverter.convertEntityToDTO(userEntity);
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> userEntity = userRepository.findByOwnerEmailAndPassword(email, password);

        if (userEntity.isPresent()) {
            userDTO = userConverter.convertEntityToDTO(userEntity.get());
        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage("Incorrect Email and Password");
            errorModelList.add(errorModel);
            throw new BusinessException(errorModelList);
        }
        return userDTO;
    }

}
