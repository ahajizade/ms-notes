package ms.notes.dao.repository;

import java.util.Optional;
import ms.notes.dao.entity.LikeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepository extends MongoRepository<LikeEntity, String> {

    Long countByNoteId(String noteId);

    Optional<LikeEntity> findByUserIdAndNoteId(String userId, String noteId);
}
