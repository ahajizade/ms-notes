package ms.notes.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import ms.notes.model.NoteDto;
import ms.notes.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService service;

    @GetMapping
    public List<NoteDto> list() {
        return service.getNotes();
    }

    @GetMapping("/{id}")
    public NoteDto get(@PathVariable String id) {
        return service.getNoteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDto create(@Valid @RequestBody NoteDto note) {
        return service.createNote(note);
    }

    @PutMapping("/{id}")
    public NoteDto update(@PathVariable String id, @RequestBody NoteDto note) {
       return service.updateNote(note, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String id) {
        service.removeNote(id);
    }

    @PutMapping("/{id}/like")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDto addLike(@RequestHeader(name = "User-Id") String userId,
                           @PathVariable String id) {
        return service.addLike(id, userId);
    }

    @DeleteMapping("/{id}/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public NoteDto removeLike(@RequestHeader(name = "User-Id") String userId,
                              @PathVariable String id) {
        return service.removeLike(id, userId);
    }
}
