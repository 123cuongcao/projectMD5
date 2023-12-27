package ra.academy.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ra.academy.service.IUserService;

@Component
@RequiredArgsConstructor
public class EmailValidator implements ConstraintValidator<EmailUnique,String> {
private final IUserService userService;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.existsByEmail(email);
    }

}
