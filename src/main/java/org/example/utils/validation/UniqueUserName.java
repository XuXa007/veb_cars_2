package org.example.utils.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy =  UniqueUserNameValidator.class)
public @interface UniqueUserName {
    String message() default "User with this name already exist";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
