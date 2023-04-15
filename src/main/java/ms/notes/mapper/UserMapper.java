package ms.notes.mapper;

import java.util.List;
import ms.notes.dao.entity.UserEntity;
import ms.notes.model.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserMapper {

    public static final UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    public abstract UserDto entityToDto(UserEntity entity);

    public abstract List<UserDto> entitiesToDtos(List<UserEntity> entities);
}
