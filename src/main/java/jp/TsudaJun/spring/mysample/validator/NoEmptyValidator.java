package jp.TsudaJun.spring.mysample.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.TsudaJun.spring.mysample.validation.NoEmpty;

public class NoEmptyValidator implements ConstraintValidator<NoEmpty, String>{
	
	public void initialize(NoEmpty annotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
    		
    		if(value.equals("") || value == null) {
    			return false;
    		}
    		return true;
    }


}
