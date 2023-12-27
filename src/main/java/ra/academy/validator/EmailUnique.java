package ra.academy.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = EmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EmailUnique {
    String message() default "email đã tồn tại !";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
