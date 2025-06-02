package com.college.yi.ecsite.front.form;

import java.util.Map;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CartUpdateForm {
@NotEmpty
	    private Map<Long, @Min(1) Integer> productQuantities;
}
