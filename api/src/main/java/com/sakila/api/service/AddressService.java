package com.sakila.api.service;

import java.util.List;
import com.sakila.api.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.entity.AddressEntity;

@Service
@Transactional
public class AddressService {

    private AddressRepository addressRepository;
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
  
	
	// 전체 조회
	public List<AddressEntity> findAll() {
		return addressRepository.findAll();
    }
	
	
}