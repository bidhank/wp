package com.wp.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.wp.dto.ContactDtlDto;
import com.wp.repo.ContactDtlRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MobileNumberValidator implements ConstraintValidator<MobileNumber, String> {

    @Autowired
    ContactDtlRepo contactDtlRepo;

    private String message;

    @Override
    public void initialize(MobileNumber constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        try {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            Phonenumber.PhoneNumber phoneNumber = phoneUtil.parse(value, null);
            boolean isValidNumber= phoneUtil.isValidNumber(phoneNumber);
            if(!isValidNumber){
                message="Invalid mobile number";
            }
            Optional<ContactDtlDto> cdd= contactDtlRepo.getByMobileNumber(value);
            boolean isDuplicate=!cdd.isEmpty();
            if(isDuplicate){
                message="Mobile number already exists";
            }
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

            return isValidNumber && !isDuplicate;
        } catch (NumberParseException e) {
            return false;
        }
    }
}

