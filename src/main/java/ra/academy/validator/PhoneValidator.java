package ra.academy.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ra.academy.service.IUserService;

@Component
@RequiredArgsConstructor
public class PhoneValidator implements ConstraintValidator<PhoneUnique,String> {
    private final IUserService userService;
    @Override
    public boolean isValid(String phone, ConstraintValidatorContext context) {
        return !userService.existsByPhone(phone);
    }
}
