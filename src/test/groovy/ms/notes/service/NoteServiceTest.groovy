package ms.notes.service

import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import ms.notes.dao.entity.LikeEntity
import ms.notes.dao.entity.NoteEntity
import ms.notes.dao.repository.LikeRepository
import ms.notes.dao.repository.NoteRepository
import ms.notes.mapper.NoteMapper
import ms.notes.model.NoteDto
import spock.lang.Specification

import java.time.LocalDateTime

class NoteServiceTest extends Specification {

    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    private NoteRepository noteRepository
    private LikeRepository likeRepository
    private NoteService service

    void setup() {
        noteRepository = Mock()
        likeRepository = Mock()
        service = new NoteService(noteRepository, likeRepository)
    }

    def noteId = "1234"
    def entity = random.nextObject(NoteEntity)
    def likeEnity = random.nextObject(LikeEntity)

    def "GetNotes"() {
        when:
        service.getNotes()

        then:
        1 * noteRepository.findAllByOrderByCreatedAtDesc() >> List.of(entity)
        1 * likeRepository.findAll() >> List.of(likeEnity)
    }

    def "GetNoteById"() {
        given:
        entity.id = noteId
        likeEnity.noteId = noteId

        when:
        def result = service.getNoteById(noteId)

        then:
        1 * noteRepository.findById(noteId) >> Optional.of(entity)
        1 * likeRepository.countByNoteId(noteId) >> 2L

        result.likes == 2
        result.content == entity.content
    }

    def "CreateNote"() {
        given:
        def dto = random.nextObject(NoteDto)
        def entity = NoteMapper.MAPPER.dtoToEntity(dto);
        entity.setCreatedAt(LocalDateTime.now())
        entity.setUpdatedAt(LocalDateTime.now())

        when:
        service.createNote(dto)

        then:
        1 * noteRepository.save(_) >> entity
    }

    def "UpdateNote"() {
        given:
        def dto = random.nextObject(NoteDto)
        NoteMapper.MAPPER.updateEntity(dto, entity);

        when:
        service.updateNote(dto, noteId)

        then:
        1 * noteRepository.findById(noteId) >> Optional.of(entity)
        1 * noteRepository.save(entity)
    }

    def "RemoveNote"() {
        when:
        service.removeNote(noteId)

        then:
        1 * noteRepository.findById(noteId) >> Optional.of(entity)
        1 * noteRepository.delete(entity)
    }
}
