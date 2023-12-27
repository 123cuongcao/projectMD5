package ra.academy.model.dto.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ra.academy.model.entity.User;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressRequest {


    private String full_address;


    private String phone;


    private String receiver_name;
}
