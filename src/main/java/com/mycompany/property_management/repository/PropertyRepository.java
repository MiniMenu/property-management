package com.mycompany.property_management.repository;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.property_management.entity.PropertyEntity;

public interface PropertyRepository extends CrudRepository<PropertyEntity, Long> {

}
