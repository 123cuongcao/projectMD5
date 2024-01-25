package ra.academy.model.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ra.academy.model.entity.Status;

import java.util.Date;

@Getter
@Setter
@Data
@Builder
public class OrderResponse {
    private String serialNumber;
    private Double totalPrice;
    private String note;
    private Status status;
    private Date createdAt;
}
