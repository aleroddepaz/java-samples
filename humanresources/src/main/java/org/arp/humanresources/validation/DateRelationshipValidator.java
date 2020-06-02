package org.arp.humanresources.validation;

import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateRelationshipValidator implements ConstraintValidator<ValidDateRelationship, TemporalEntity> {

    @Override
    public void initialize(ValidDateRelationship annotation) {
    }

    @Override
    public boolean isValid(TemporalEntity value, ConstraintValidatorContext context) {
    	Date startDate = value.getStartDate();
    	Date endDate = value.getEndDate();
        if (startDate != null && endDate != null) {
            return startDate.before(endDate);
        }
        return true;
    }

}
