package com.sakila.api.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sakila.api.dto.CityDto;
import com.sakila.api.entity.CityEntity;
import com.sakila.api.entity.CountryEntity;
import com.sakila.api.repository.CityRepository;
import com.sakila.api.repository.CountryRepository;

@Service
@Transactional
public class CityService {
	private CountryRepository countryRepository;
	private CityRepository cityRepository;
	
	public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
		this.cityRepository = cityRepository;
		this.countryRepository = countryRepository;
	}
	
	// city 삭제
	public boolean delete(int cityId) {
		if(cityRepository.existsById(cityId)) {
			cityRepository.deleteById(cityId);
			return true;
		}
		return false;
	}
	
	// city 수정
	public void update(CityDto cityDto) {
		CityEntity updateCityEntity = cityRepository.findById(cityDto.getCityId()).orElse(null);
		updateCityEntity.setCity(cityDto.getCity());
		
		// countryEntity
		CountryEntity updateCountryEntity = countryRepository.findById(cityDto.getCountryId()).orElse(null);
		updateCityEntity.setCountryEntity(updateCountryEntity);
	}
	
	// CityEntity 입력
	public void save(CityDto cityDto) {
		// DTO -> Entity
		CityEntity saveCityEntity = new CityEntity();
		saveCityEntity.setCity(cityDto.getCity());
		
		// countryEntity
		CountryEntity countryEntity = countryRepository.findById(cityDto.getCountryId()).orElse(null);
		saveCityEntity.setCountryEntity(countryEntity);
		
		cityRepository.save(saveCityEntity);
	}
	
	// 전체조회
	public List<CityEntity> findAll() {
		return cityRepository.findAll();
	}
}