package ms.notes;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import ms.notes.dao.entity.NoteEntity;
import ms.notes.dao.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@AllArgsConstructor
public class NotesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(NotesApplication.class, args);
    }

    private final NoteRepository repository;

    @Override
    public void run(String... args) {
//        repository.save(NoteEntity.builder()
//                .content("Draft note 2")
//                .likes(1L)
//                .createdAt(LocalDateTime.now())
//                .updatedAt(LocalDateTime.now())
//                .build());
//
//        var response = repository.findAll();
//        System.out.println("response:: " + response);
    }
}
