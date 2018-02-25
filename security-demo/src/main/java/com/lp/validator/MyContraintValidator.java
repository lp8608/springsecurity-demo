package com.lp.validator;

import  javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class MyContraintValidator implements ConstraintValidator<MyContraint,Object>{


    @Override
    public void initialize(MyContraint constraintAnnotation) {
        System.out.println("my validator initialize");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        System.out.println(value);
        return true;
    }
}
