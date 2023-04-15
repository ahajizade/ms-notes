package ms.notes.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NoteDto {

    private String id;
    @NotBlank
    private String content;
    private Long likes;
    private LocalDateTime createdAt;
}
