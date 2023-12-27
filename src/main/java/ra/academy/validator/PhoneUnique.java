package ra.academy.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = PhoneValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PhoneUnique {
    String message() default "số điện thoại đã tồn tại đã tồn tại !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
