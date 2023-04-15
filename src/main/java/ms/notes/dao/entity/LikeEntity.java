package ms.notes.dao.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "likes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeEntity {

    @Id
    private String id;
    private String userId;
    private String noteId;
}
