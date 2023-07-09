package com.erp.organization.validators;

import jakarta.validation.constraints.Email;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Validation annotation combining our rules for a valid email address.
 * 
 * @author marc
 */
@Email
@Size(min = 5, max = 100)
@NotNull
@Constraint(validatedBy = {})
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidErpEmail {

	String message() default "{docdok.validDocdokEmail}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}