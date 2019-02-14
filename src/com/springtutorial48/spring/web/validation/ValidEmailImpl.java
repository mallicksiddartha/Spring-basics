package com.springtutorial48.spring.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.*;;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String>{
	private int minCharLength;
	
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		
		minCharLength = constraintAnnotation.min();
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if(email.length() < minCharLength) {
			return false;
		}
		if(!EmailValidator.getInstance(false).isValid(email)) {
			return false;
		}
		
		
		return true;
	}

}
