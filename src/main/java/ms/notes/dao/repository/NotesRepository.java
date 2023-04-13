package ms.notes.dao.repository;

import ms.notes.dao.entity.NotesEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotesRepository extends MongoRepository<NotesEntity, Long> {


}
