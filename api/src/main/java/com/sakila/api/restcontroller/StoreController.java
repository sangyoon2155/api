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

import com.sakila.api.dto.StoreDto;
import com.sakila.api.entity.StoreEntity;
import com.sakila.api.service.StoreService;

@RestController
public class StoreController {
	private StoreService storeService;
	
	// 필드주입 대신 생성자 주입을 사용
	public StoreController(StoreService storeService) {
		this.storeService = storeService;
	}
	
	// 전체조회
	@GetMapping("/store")
	public ResponseEntity<List<StoreEntity>> store() {
		return new ResponseEntity<List<StoreEntity>> (storeService.findAll(), HttpStatus.OK);
	}
	
	// 한 행 조회
	@GetMapping("/storeOne/{storeId}")
	public ResponseEntity<StoreEntity> storeOne(@PathVariable int storeId) {
		return new ResponseEntity<StoreEntity> (storeService.findById(storeId), HttpStatus.OK);
	}
	
	// 입력
	@PostMapping("/store")
	public ResponseEntity<String> store(@RequestBody StoreDto storeDto) {
		System.out.println(storeDto.toString());
		storeService.save(storeDto);
		return new ResponseEntity<String>("입력성공", HttpStatus.CREATED);
	}

	// 수정
	@PatchMapping("/updateStore")
	public ResponseEntity<String> updateStore(@RequestBody StoreDto storeDto) {
		storeService.update(storeDto);
		return new ResponseEntity<String>("수정성공", HttpStatus.OK);
	}
	
	// 삭제
	@DeleteMapping("/deleteStore/{storeId}")
	public ResponseEntity<String> deleteStore(@PathVariable int storeId) {
		
		boolean result = storeService.delete(storeId);
		if(result) {
			return new ResponseEntity<String>("삭제성공", HttpStatus.OK);
		}
		return new ResponseEntity<String>("삭제실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
}