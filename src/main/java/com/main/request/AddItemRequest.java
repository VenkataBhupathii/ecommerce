package com.main.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest {

	private Long productId;
	
	private String size;
	
	private int quantity;
	
	private Integer price;
}
