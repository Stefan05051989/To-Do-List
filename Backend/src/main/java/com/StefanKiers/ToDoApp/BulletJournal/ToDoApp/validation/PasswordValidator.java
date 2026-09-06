package com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * PasswordValidator
 * Challenge: com.StefanKiers.ToDoApp.BulletJournal.ToDoApp.validation
 *
 * @author Stefan Kiers
 * @since 6-9-2026
 */

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    // vereist minimaal één letter, één cijfer, en een minimum lengte van 12 tekens.
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d).{12,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        if (password == null) {
            return false;
        }
        return password.matches(PASSWORD_PATTERN);
    }
}
