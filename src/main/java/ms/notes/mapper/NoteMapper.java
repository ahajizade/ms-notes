package ms.notes.mapper;

import java.util.List;
import ms.notes.dao.entity.NoteEntity;
import ms.notes.model.NoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class NoteMapper {

    public static final NoteMapper MAPPER = Mappers.getMapper(NoteMapper.class);

    public abstract NoteDto entityToDto(NoteEntity entity);

    public abstract List<NoteDto> entitiesToDtos(List<NoteEntity> entities);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "likes", ignore = true)
    public abstract NoteEntity dtoToEntity(NoteDto dto);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", expression = "java(LocalDateTime.now())")
    public abstract void updateEntity(NoteDto dto, @MappingTarget NoteEntity entity);
}
