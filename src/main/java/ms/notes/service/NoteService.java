package ms.notes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.notes.dao.entity.LikeEntity;
import ms.notes.dao.entity.NoteEntity;
import ms.notes.dao.repository.LikeRepository;
import ms.notes.dao.repository.NoteRepository;
import ms.notes.exception.ResourceNotFoundException;
import ms.notes.mapper.NoteMapper;
import ms.notes.model.NoteDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;
    private final LikeRepository likeRepository;

    public List<NoteDto> getNotes() {
        log.info("ActionLog.getNotes.start");
        var entities = repository.findAllByOrderByCreatedAtDesc();
        Map<String, Long> likes = likeRepository.findAll().stream()
                .collect(Collectors.groupingBy(LikeEntity::getNoteId, Collectors.counting()));
        var dtos = NoteMapper.MAPPER.entitiesToDtos(entities);
        dtos.forEach(e -> e.setLikes(likes.get(e.getId()) != null ? likes.get(e.getId()) : 0L));
        log.info("ActionLog.getNotes.end");
        return dtos;
    }

    public NoteDto getNoteById(String id) {
        log.info("ActionLog.getNoteById.start: id {}", id);
        var entity = findNoteById(id);
        var likes = likeRepository.countByNoteId(entity.getId());
        var dto = NoteMapper.MAPPER.entityToDto(entity);
        dto.setLikes(likes);
        log.info("ActionLog.getNoteById.end: id {}", id);
        return dto;
    }

    public NoteDto createNote(NoteDto note) {
        log.info("ActionLog.createNote.start");
        var request = NoteMapper.MAPPER.dtoToEntity(note);
        request.setCreatedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        var response = NoteMapper.MAPPER.entityToDto(repository.save(request));
        log.info("ActionLog.createNote.end");
        return response;
    }

    public NoteDto updateNote(NoteDto note, String id) {
        log.info("ActionLog.updateNote.start: id {}", id);
        var entity = findNoteById(id);
        NoteMapper.MAPPER.updateEntity(note, entity);
        repository.save(entity);
        var dto = NoteMapper.MAPPER.entityToDto(entity);
        log.info("ActionLog.updateNote.end: id {}", id);
        return dto;
    }

    public void removeNote(String id) {
        log.info("ActionLog.removeNote.start: id {}", id);
        var entity = findNoteById(id);
        repository.delete(entity);
        log.info("ActionLog.removeNote.end: id {}", id);
    }

    public NoteDto addLike(String noteId, Authentication authentication) {
        log.info("ActionLog.addLike.start: id {}, username {}", noteId, authentication.getName());
        findNoteById(noteId); //validation for note. If note don't find will throw exception
        likeRepository.findByUserIdAndNoteId(authentication.getName(), noteId)
                .orElseGet(() -> likeRepository.save(LikeEntity.builder().noteId(noteId).userId(authentication.getName()).build()));
        var dto = getNoteById(noteId);
        log.info("ActionLog.addLike.end: id {}, username {}", noteId, authentication.getName());
        return dto;
    }

    public NoteDto removeLike(String noteId, String username) {
        log.info("ActionLog.removeLike.start: id {}, username {}", noteId, username);
        findNoteById(noteId); //validation for note. If note don't find will throw exception
        var likeEntity = likeRepository.findByUserIdAndNoteId(username, noteId).orElseThrow(() ->
                new ResourceNotFoundException("exception.ms-notes.like-not-found"));
        likeRepository.delete(likeEntity);
        var dto = getNoteById(noteId);
        log.info("ActionLog.removeLike.start: id {}, userId {}", noteId, username);
        return dto;
    }

    private NoteEntity findNoteById(String id) {
        return repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("exception.ms-notes.note-not-found"));
    }
}
