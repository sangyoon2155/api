package com.sakila.api.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "city")
@DynamicInsert
@Getter
@Setter
public class CityEntity { // // city -다대일- country
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private int cityId;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "last_update", nullable = true)
	private Timestamp lastUpdate;
	
	// 다(city)대일(country)
	@ManyToOne 
	@JoinColumn(name = "country_id") // 외래키 컬럼이름, 단방향(city에서 country) 
	private CountryEntity countryEntity;
}