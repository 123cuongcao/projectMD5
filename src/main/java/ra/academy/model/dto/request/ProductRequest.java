package ra.academy.model.dto.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import ra.academy.model.entity.Categories;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private Long id;
    private String sku;
    @NotBlank
    private String name;

    private String description;
    private Double unit_price;
    private int stock_quantity;
    private MultipartFile image;
    private Long categoryId;
    private String status;
}
