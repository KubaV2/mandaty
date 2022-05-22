package com.pirko.mandaty.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsName {

    String message() default "Poprawny format to pierwsza duża litera oraz reszta małych (max 45 znaków)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
