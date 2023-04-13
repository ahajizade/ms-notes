package ms.notes.service;

import lombok.AllArgsConstructor;
import ms.notes.dao.repository.NotesRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotesService {

    private final NotesRepository repository;
}
