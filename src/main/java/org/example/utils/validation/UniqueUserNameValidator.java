package org.example.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.repo.UsersRepository;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {
    private final UsersRepository usersRepository;

    public UniqueUserNameValidator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return usersRepository.findByUserName(value).isEmpty();
    }
}
