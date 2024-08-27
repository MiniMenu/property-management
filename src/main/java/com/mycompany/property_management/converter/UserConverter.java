package com.mycompany.property_management.converter;

import org.springframework.stereotype.Component;

import com.mycompany.property_management.dto.UserDTO;
import com.mycompany.property_management.entity.UserEntity;

@Component
public class UserConverter {

    public UserEntity convertDTOToEntity(UserDTO userdto) {

        UserEntity userEntity = new UserEntity();

        userEntity.setOwnerName(userdto.getOwnerName());
        userEntity.setOwnerEmail(userdto.getOwnerEmail());
        userEntity.setPassword(userdto.getPassword());
        userEntity.setPhone(userdto.getPhone());

        return userEntity;

    }

    public UserDTO convertEntityToDTO(UserEntity userEntity) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setOwnerName(userEntity.getOwnerName());
        userDTO.setOwnerEmail(userEntity.getOwnerEmail());
        userDTO.setPhone(userEntity.getPhone());

        return userDTO;

    }
}
