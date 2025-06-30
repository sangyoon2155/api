package com.sakila.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;

import com.sakila.api.dto.AddressDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CityRepository;

@Service
@Transactional
public class AddressService {
	private AddressRepository addressRepository;
	private CityRepository cityRepository;
	
	public AddressService(AddressRepository addressRepository, CityRepository cityRepository) {
		this.addressRepository = addressRepository;
		this.cityRepository = cityRepository;
	}
	
	// 수정
	public void update(AddressDto addressDto) {
		AddressEntity updateAddress = addressRepository.findById(addressDto.getAddressId()).orElse(null);
		updateAddress.setAddress(addressDto.getAddress());
		updateAddress.setAddress2(addressDto.getAddress2());
		updateAddress.setDistrict(addressDto.getDistrict());
		updateAddress.setPhone(addressDto.getPhone());
		updateAddress.setPostalCode(addressDto.getPostalCode());
	}
	

	// 입력
	public void save(AddressDto addressDto) {
		AddressEntity saveAddressEntity = new AddressEntity();
		saveAddressEntity.setAddress(addressDto.getAddress());
		saveAddressEntity.setAddress2(addressDto.getAddress2());
		saveAddressEntity.setDistrict(addressDto.getDistrict());
		saveAddressEntity.setPostalCode(addressDto.getPostalCode());
		saveAddressEntity.setPhone(addressDto.getPhone());
		
		CityEntity cityEntity = cityRepository.findById(addressDto.getCityId()).orElse(null);
		saveAddressEntity.setCityEntity(cityEntity);
		addressRepository.save(saveAddressEntity);
	}
	
	// 전체조회
	public List<AddressEntity> findAll() {
		return addressRepository.findAll();
	}
}