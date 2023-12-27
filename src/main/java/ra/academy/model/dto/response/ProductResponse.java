package ra.academy.model.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductResponse {
    private Long id;
    private String description;
    private String productName;
    private Double unitPrice;
    private String imageUrl;

}
