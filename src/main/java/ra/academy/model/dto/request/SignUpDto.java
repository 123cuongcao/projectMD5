package ra.academy.model.dto.request;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.parameters.P;
import org.springframework.web.multipart.MultipartFile;
import ra.academy.validator.EmailUnique;
import ra.academy.validator.PasswordMatching;
import ra.academy.validator.PhoneUnique;
import ra.academy.validator.UserNameUnique;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@PasswordMatching(password = "password",
        confirmPassword = "confirmPassword")
public class SignUpDto {
    @UserNameUnique
    @NotBlank(message = "Không được để trống")
    @Size(min = 5, max = 10, message = "Độ dài từ 5 đến 10 ký tự")
    private String username;

    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{5,}$", message = "Mật khẩu sai định dạng (dộ dài ít nhất 5 ký tự gồm 1 chữ hoa và 1 số)")
    private String password;
    @NotBlank(message = "Không được để trống")
    private String fullname;

    private String confirmPassword;

    @EmailUnique
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",message = "Email không đúng định dạng(a@gmail.com)")
    private String email;

    @PhoneUnique
    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b",message = "Số điện thoại không đúng định dạng")
    private String phone;

    private MultipartFile avatar;

    private Set<String> listRoles;

    @NotBlank(message = "Không được để trống")
    private String address;

}
