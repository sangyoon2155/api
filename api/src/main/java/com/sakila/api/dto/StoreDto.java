package com.sakila.api.dto;

import java.sql.Timestamp;

import com.sakila.api.entity.AddressEntity;

import lombok.Data;

@Data
public class StoreDto {
	private int storeId;
	
	private int managerStaffId;
	
	private Timestamp lastUpdate;
	
	private int addressId;
}