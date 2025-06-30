package com.sakila.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CustomerDto;
import com.sakila.api.entity.AddressEntity;
import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.repository.AddressRepository;
import com.sakila.api.repository.CustomerRepository;
import com.sakila.api.repository.StoreRepository;

@Service
@Transactional
public class CustomerService {
	private CustomerRepository customerRepository; // 생성자 주입
	private StoreRepository storeRepository;
	private AddressRepository addressRepository;

	@Autowired // 생략 가능
	public CustomerService(CustomerRepository customerRepository,
						   StoreRepository storeRepository,
						   AddressRepository addreRepository) {
		
		this.customerRepository = customerRepository;
		this.storeRepository = storeRepository;
		this.addressRepository = addreRepository;
	}
	
	// 전체조회
	public List<CustomerEntity> findAll() {
		return customerRepository.findAll();
	}
	
	// customer 입력
	public void save(CustomerDto customerDto) {
		// Dto -> Entity
		CustomerEntity saveCustomerEntity = new CustomerEntity();
		saveCustomerEntity.setFirstName(customerDto.getFirstName());
		saveCustomerEntity.setLastName(customerDto.getLastName());
		saveCustomerEntity.setEmail(customerDto.getEmail());
		saveCustomerEntity.setActive(customerDto.getActive());
		
		StoreEntity storeEntity = storeRepository.findById(customerDto.getStoreId()).orElse(null);
		saveCustomerEntity.setStoreEntity(storeEntity);
		
		AddressEntity addressEntity = addressRepository.findById(customerDto.getAddressId()).orElse(null);
		saveCustomerEntity.setAddressEntity(addressEntity);
		
		customerRepository.save(saveCustomerEntity);
		
	}
	
	// customer 수정
	public void update(CustomerDto customerDto) {
		CustomerEntity updateCustomer = customerRepository.findById(customerDto.getCustomerId()).orElse(null);
		updateCustomer.setFirstName(customerDto.getFirstName());
		updateCustomer.setLastName(customerDto.getLastName());
		updateCustomer.setEmail(customerDto.getEmail());
		updateCustomer.setActive(customerDto.getActive());
	}
	
	// customer 삭제
	public boolean delete(int customerId) {
		if(customerRepository.existsById(customerId)) {
			customerRepository.deleteById(customerId);
			return true;
		}
		return false;
	}
	
	// 한 행 조회
	public CustomerEntity findById(int customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}
	
}