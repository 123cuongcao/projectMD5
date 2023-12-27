package ra.academy.model.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.academy.model.entity.Products;
import ra.academy.validator.CategoryUnique;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryRequest {

    private Long id;
    @NotBlank

    private String name;

    @Size(max = 100)
    private String description;

    private boolean status = true;

    private List<Products> products;

}
