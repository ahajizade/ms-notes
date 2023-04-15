package ms.notes.dao.repository;

import java.util.Optional;
import ms.notes.dao.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {

    Optional<UserEntity> findByUsername(String username);
}
