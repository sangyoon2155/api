package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CountryDto;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CountryRepository;

@Service
@Transactional
public class CountryService {
	private CountryRepository countryRepository;
	private CityRepository cityRepository;
	
	// 필드주입 대신 생성자 주입을 사용
	public CountryService(CountryRepository countryRepository, CityRepository cityRepository) {
		this.countryRepository = countryRepository;
		this.cityRepository = cityRepository;
	}
	
	// country 삭제
	public boolean delete(int countryId) {
		// 자식테이블에 참조하는 행이 없다면 (select count(*) from city where country_id
		// 이슈 : 자식테이블에 외래키로 참조하는 행이 있다면?
		if(0 == cityRepository.countByCountryEntity(countryRepository.findById(countryId).orElse(null))) {
			countryRepository.deleteById(countryId);
			return true;
		}
		return false;
	}
	
	// country 수정
	public void update(CountryDto countryDto) {
		CountryEntity updateCountryEntity = countryRepository.findById(countryDto.getCountryId()).orElse(null);
		updateCountryEntity.setCountry(countryDto.getCountry());
	}
	
	// CountryEntity 입력
	public void save(CountryDto countryDto) {
		CountryEntity saveCountryEntity = new CountryEntity();
		saveCountryEntity.setCountry(countryDto.getCountry());
		countryRepository.save(saveCountryEntity);
	}
	
	// 전체조회
	public List<CountryEntity> findAll() {
		return countryRepository.findAll();
	}
	
	// 한 행 조회
	public CountryEntity findById(int countryId) {
		return countryRepository.findById(countryId).orElse(null);
	}
}