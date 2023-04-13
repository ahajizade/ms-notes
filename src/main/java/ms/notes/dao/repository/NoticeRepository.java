package ms.notes.dao.repository;

import ms.notes.dao.entity.NoticeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoticeRepository extends MongoRepository<NoticeEntity, Long> {


}
