package ra.academy.model.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserCatalogResponse {
    private Long id;
    private String categoryName;
    private String description;
}
