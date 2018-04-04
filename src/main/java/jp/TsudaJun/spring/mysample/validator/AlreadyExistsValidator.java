package jp.TsudaJun.spring.mysample.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.TsudaJun.spring.mysample.DAO.UserDao;
import jp.TsudaJun.spring.mysample.model.User;
import jp.TsudaJun.spring.mysample.validation.AlreadyExists;

public class AlreadyExistsValidator implements ConstraintValidator<AlreadyExists, String>{

	@Autowired
	UserDao dao;
	
	public void initialize(AlreadyExists annotation) {
    }

    public boolean isValid(String value, ConstraintValidatorContext context) {
    		
    		if(dao != null) {
	    		User user = dao.getUserById(value);
	    		if(user != null)
	    			return false;
    		}
    		return true;
    }

}
