package ms.notes.model;

import lombok.Data;

@Data
public class ExceptionResponse {
    private String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }
}
