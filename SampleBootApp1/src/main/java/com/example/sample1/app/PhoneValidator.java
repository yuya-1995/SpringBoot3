package com.example.sample1.app;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator 
	implements ConstraintValidator<Phone, String> {
	private boolean onlyNumber = false;

    @Override
    public void initialize(Phone phone) {
    }

    @Override
    public boolean isValid(String input,
    		ConstraintValidatorContext cxt) {
        if (input == null) {
            return true;
        }
        if (onlyNumber) {
			return input.matches("[0-9]*");
		} else {
			return input.matches("[0-9()-]*");
		}
    }
}
