package ra.academy.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class SignInDto {
    @NotBlank(message = "Không được để trống")
    private String username;
    @NotBlank(message = "Không được để trống")
    private String password;
}
