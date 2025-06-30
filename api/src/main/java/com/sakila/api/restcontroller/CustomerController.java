package com.sakila.api.restcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sakila.api.dto.CustomerDto;
import com.sakila.api.entity.CustomerEntity;
import com.sakila.api.service.CustomerService;

@RestController
public class CustomerController {
	private CustomerService customerService;

	// 필드주입 대신 생성자 주입을 사용
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	
	// 전체조회
	@GetMapping("/customer")
	public ResponseEntity<List<CustomerEntity>> customer() {
		return new ResponseEntity<List<CustomerEntity>>(customerService.findAll(), HttpStatus.OK);
	}
	
	// 입력
	@PostMapping("/customer")
	public ResponseEntity<String> customer(@RequestBody CustomerDto customerDto) {
		System.out.println(customerDto.toString());
		customerService.save(customerDto);
		return new ResponseEntity<String>("입력성공", HttpStatus.CREATED);
	}
	
	// 수정
	@PatchMapping("/customer")
	public ResponseEntity<String> updateCustomer(@RequestBody CustomerDto customerDto) {
		customerService.update(customerDto);
		return new ResponseEntity<String>("수정성공", HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/customer/{customerId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
		
		boolean result = customerService.delete(customerId);
		if(result) {
			return new ResponseEntity<String>("삭제성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("삭제실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 한행 조회
	@GetMapping("/customerOne/{customerId}")
	public ResponseEntity<CustomerEntity> customerOne(@PathVariable int customerId) {
		return new ResponseEntity<CustomerEntity> (customerService.findById(customerId), HttpStatus.OK);
	}
	
	

}