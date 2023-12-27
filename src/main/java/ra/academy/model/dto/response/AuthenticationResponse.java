package ra.academy.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthenticationResponse {
    private String username;
    private String fullName;
    private String email;
    private String phone;
    private final String type = "Bearer";
    private String accessToken;
    private String refreshToken;
    private List<String> list;

}
