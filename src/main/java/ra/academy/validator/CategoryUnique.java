package ra.academy.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Constraint(validatedBy = CategoryValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CategoryUnique {
    String message() default "Danh mục đã tồn tại";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
