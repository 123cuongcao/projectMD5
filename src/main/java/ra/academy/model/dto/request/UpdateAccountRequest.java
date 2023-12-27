package ra.academy.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UpdateAccountRequest {



    @NotBlank(message = "Không được để trống")
    private String fullname;

    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "Email không đúng định dạng(a@gmail.com)")
    private String email;

    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b",message = "Số điện thoại không đúng định dạng")
    private String phone;

    private MultipartFile avatar;

    private Set<String> listRoles;

    @NotBlank(message = "Không được để trống")
    private String address;
}
