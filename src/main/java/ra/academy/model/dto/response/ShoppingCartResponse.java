package ra.academy.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ShoppingCartResponse {
    private Long id ;
    private String productName;
    private Double unitPrice;
    private Integer quantity;
}
