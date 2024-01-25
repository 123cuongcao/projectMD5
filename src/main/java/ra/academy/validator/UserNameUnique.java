package ra.academy.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = UserNameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserNameUnique {
    String message() default "tài khoản người dùng đã tồn tại !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}