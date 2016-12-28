package gamification.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import gamification.model.User;


public class PasswordsEqualConstraintValidator implements
    ConstraintValidator<PasswordsEqualConstraint, Object> {

@Override
public void initialize(PasswordsEqualConstraint arg0) {
}

@Override
public boolean isValid(Object candidate, ConstraintValidatorContext arg1) {
    User user = (User) candidate;
    return user.getPassword().equals(user.getConfirm_password());
}
}