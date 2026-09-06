package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ValidPassword
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.validation
 *
 * @author Stefan Kiers
 * @since 6-9-2026
 */

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
    String message() default "Wachtwoord moet minimaal 8 tekens, waarvan één letter en 1 cijfer bevatten.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
