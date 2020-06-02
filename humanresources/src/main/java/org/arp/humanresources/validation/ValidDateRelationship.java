package org.arp.humanresources.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(value = { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DateRelationshipValidator.class })
@Documented
public @interface ValidDateRelationship {

	String message() default "{global.dateRelationship}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}