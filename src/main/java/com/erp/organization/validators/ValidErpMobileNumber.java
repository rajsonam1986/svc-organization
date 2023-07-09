package com.erp.organization.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Validation annotation combining our rules for a valid mobile number.
 *
 * @author Raj
 */
@Pattern(regexp = ValidErpMobileNumber.E164_REGEX)
@Size(min = 5, max = 20)
@NotNull
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidErpMobileNumber {

    // Regex for valid E164 phone numbers +41VVNNNMMII
    public static final String E164_REGEX = "^\\+[1-9]\\d{1,14}$";

    String message() default "{erp.validDocdokMobileNumber}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}