package com.main.request;

import java.util.HashSet;
import java.util.Set;

import com.main.model.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class CreateProductRequest {

	private String title;
	
	private String description;
	
	private int price;
	
	private int discountPrice;
	
	private int discountPercent;
	
	private int quantity;
	
	private String color;
	
	private String brand;
	
	private Set<Size> size=new HashSet<>();
	
	private String imageUrl;
	
	private String topLevelCategory;
	
	private String secondLevelCategory;
	
	private String thirdLevelCategory;
	
	
}
