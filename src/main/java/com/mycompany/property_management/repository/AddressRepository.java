package com.mycompany.property_management.repository;

import org.springframework.data.repository.CrudRepository;

import com.mycompany.property_management.entity.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {

}
