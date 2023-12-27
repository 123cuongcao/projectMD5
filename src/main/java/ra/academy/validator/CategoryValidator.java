package ra.academy.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ra.academy.model.dto.request.CategoryRequest;
import ra.academy.model.entity.Categories;
import ra.academy.repository.ICategoryRepository;
import ra.academy.service.ICategoryService;

import java.util.Optional;

@Component
@RequiredArgsConstructor

public class CategoryValidator implements ConstraintValidator<CategoryUnique, String> {
    private final ICategoryRepository repository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !repository.existsByName(value);
    }
}
