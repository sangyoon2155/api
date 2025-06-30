package com.sakila.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.StoreDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class StoreService {
	private StoreRepository storeRepository; // 필드주입 -> 생성자 주입
	private AddressRepository addressRepository;

	@Autowired  // 생략 가능
	public StoreService(StoreRepository storeRepository, AddressRepository addressRepository) {
		// 주입전에 선행작업 or 테스트, ....
		// 외래키도 주입받아야 사용가능
		this.storeRepository = storeRepository;
		this.addressRepository = addressRepository;
	}
	
	// 전체조회
	public List<StoreEntity> findAll() {
		return storeRepository.findAll();
	}
	
	// 한 행 조회
	public StoreEntity findById(int storeId) {
		return storeRepository.findById(storeId).orElse(null);
	}
	
	// StoreEntity 입력
	public void save(StoreDto storeDto) {
		StoreEntity saveStoreEntity = new StoreEntity();
		saveStoreEntity.setManagerStaffId(storeDto.getManagerStaffId());
		// addressEntity
		AddressEntity addressEntity = addressRepository.findById(storeDto.getAddressId()).orElse(null);
		saveStoreEntity.setAddressEntity(addressEntity);

		storeRepository.save(saveStoreEntity);
	}
	
	// 수정
	public void update(StoreDto storeDto) {
		StoreEntity updateStore = storeRepository.findById(storeDto.getStoreId()).orElse(null);
		updateStore.setManagerStaffId(storeDto.getManagerStaffId());
		
		AddressEntity updateAddress = addressRepository.findById(storeDto.getAddressId()).orElse(null);
		updateStore.setAddressEntity(updateAddress);
	}
	
	// 삭제
	public boolean delete(int storeId) {
		if(storeRepository.existsById(storeId)) {
			storeRepository.deleteById(storeId);
			return true;
		}
		return false;
	}
	
}