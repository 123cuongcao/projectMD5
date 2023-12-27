package ra.academy.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ra.academy.service.IUserService;

@Component
@RequiredArgsConstructor
public class UserNameValidator implements ConstraintValidator<UserNameUnique, String> {
    private final IUserService  userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !userService.existsByUserName(username);
    }

}
