package ms.notes.dao.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "notes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteEntity {

    @Id
    private String id;
    private String content;
    private Long likes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
