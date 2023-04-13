package ms.notes;

import lombok.AllArgsConstructor;
import ms.notes.dao.repository.NotesRepository;
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

    private final NotesRepository repository;

    @Override
    public void run(String... args) throws Exception {
        var response = repository.findAll();
        System.out.println("response:: " + response);
    }
}
