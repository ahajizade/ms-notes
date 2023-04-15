package ms.notes.dao.repository;

import java.util.List;
import ms.notes.dao.entity.NoteEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<NoteEntity, String> {

    List<NoteEntity> findAllByOrderByCreatedAtDesc();
}
