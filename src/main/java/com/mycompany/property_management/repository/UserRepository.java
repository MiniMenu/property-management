package com.mycompany.property_management.repository;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.property_management.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

}
