package com.example.myproject.utils.validations.checkUserExists;

import com.example.myproject.domain.entity.UserClientEntity;
import com.example.myproject.domain.model.banding.user.UserLoginFormModel;
import com.example.myproject.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public record LoginUserValidator(UserRepository userRepository,
                                 ModelMapper modelMapper)
        implements ConstraintValidator<ValidateLoginUser, UserLoginFormModel> {

    @Override
    public void initialize(ValidateLoginUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginFormModel userLogin, ConstraintValidatorContext constraintValidatorContext) {
        Optional<UserClientEntity> loginCandidate = this.userRepository.findByUsername(userLogin.getUsername());

        return loginCandidate.isPresent()
                && loginCandidate.get()
                .getPassword()
                .equals(userLogin.getPassword());
    }
}