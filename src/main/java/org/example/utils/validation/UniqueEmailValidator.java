package org.example.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.repo.UsersRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UsersRepository usersRepository;

    public UniqueEmailValidator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return usersRepository.findUserByEmail(value).isEmpty();
    }
}
