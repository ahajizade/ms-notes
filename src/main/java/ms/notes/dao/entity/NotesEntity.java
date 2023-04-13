package ms.notes.dao.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class NotesEntity {

    @Id
    private Long id;
    private String name;
}
