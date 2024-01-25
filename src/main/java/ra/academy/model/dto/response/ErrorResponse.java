package ra.academy.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

@Data
@NoArgsConstructor

public class ErrorResponse {
    private HttpStatus statusCode;
    private String errorMessage;
    private Map<String, String> errorsDetails;

    public ErrorResponse(HttpStatus statusCode, String errorMessage) {
        this(statusCode, errorMessage, Collections.emptyMap());
    }

    public ErrorResponse(HttpStatus statusCode, String errorMessage, Map<String, String> errorsDetails) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.errorsDetails = errorsDetails;
    }

}
