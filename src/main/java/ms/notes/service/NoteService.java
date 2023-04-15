package ms.notes.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.notes.dao.entity.NoteEntity;
import ms.notes.dao.repository.NoteRepository;
import ms.notes.exception.ResourceNotFoundException;
import ms.notes.mapper.NoteMapper;
import ms.notes.model.NoteDto;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    public List<NoteDto> getNotes() {
        log.info("ActionLog.getNotes.start");
        var entities = repository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        var dtos = NoteMapper.MAPPER.entitiesToDtos(entities);
        log.info("ActionLog.getNotes.end");
        return dtos;
    }

    public NoteDto getNoteById(String id) {
        log.info("ActionLog.getNoteById.start: id {}", id);
        var entity = findNoteById(id);
        var dto = NoteMapper.MAPPER.entityToDto(entity);
        log.info("ActionLog.getNoteById.end: id {}", id);
        return dto;
    }

    public NoteDto createNote(NoteDto note) {
        log.info("ActionLog.createNote.start");
        var request = NoteMapper.MAPPER.dtoToEntity(note);
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        request.setLikes(0L);
        var response = NoteMapper.MAPPER.entityToDto(repository.save(request));
        log.info("ActionLog.createNote.end");
        return response;
    }

    @Transactional
    public void updateNote(NoteDto note, String id) {
        log.info("ActionLog.updateNote.start: id {}", id);
        var entity = findNoteById(id);
        NoteMapper.MAPPER.updateEntity(note, entity);
        log.info("ActionLog.updateNote.end: id {}", id);
    }

    public void removeNote(String id) {
        log.info("ActionLog.removeNote.start: id {}", id);
        var entity = findNoteById(id);
        repository.delete(entity);
        log.info("ActionLog.removeNote.end: id {}", id);
    }

    @Transactional
    public NoteDto addLike(String id) {
        log.info("ActionLog.addLike.start: id {}", id);
        var entity = findNoteById(id);
        entity.setLikes(entity.getLikes() + 1);
        var dto = NoteMapper.MAPPER.entityToDto(entity);
        log.info("ActionLog.addLike.end: id {}", id);
        return dto;
    }

    @Transactional
    public NoteDto removeLike(String id) {
        log.info("ActionLog.removeLike.start: id {}", id);
        var entity = findNoteById(id);
        entity.setLikes(entity.getLikes() - 1);
        var dto = NoteMapper.MAPPER.entityToDto(entity);
        log.info("ActionLog.removeLike.end: id {}", id);
        return dto;
    }

    private NoteEntity findNoteById(String id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("exception.ms-notes.note-not-found"));
    }
}
