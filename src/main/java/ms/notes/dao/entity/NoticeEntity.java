package ms.notes.dao.entity;

import javax.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class NoticeEntity {

    @Id
    private Long id;
    private String name;
}
