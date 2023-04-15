package ms.notes;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import ms.notes.dao.entity.NoteEntity;
import ms.notes.dao.entity.UserEntity;
import ms.notes.dao.repository.NoteRepository;
import ms.notes.dao.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class NotesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
    }
}
