package com.main.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RatingRequest {

	private Long productId;
	private double rating;
}
