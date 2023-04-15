package ms.notes.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.notes.dao.entity.UserEntity;
import ms.notes.dao.repository.UserRepository;
import ms.notes.exception.ResourceNotFoundException;
import ms.notes.mapper.UserMapper;
import ms.notes.model.LoginDto;
import ms.notes.model.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserDto createUser(LoginDto user) {
        log.info("ActionLog.createUser.start: username {}", user.getUsername());
        var entity = repository.save(UserEntity.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .build());
        var dto = UserMapper.MAPPER.entityToDto(entity);
        log.info("ActionLog.createUser.end: username {}", user.getUsername());
        return dto;
    }

    public List<UserDto> getUsers() {
        log.info("ActionLog.createUser.start");
        var entityList = repository.findAll();
        var dtoList = UserMapper.MAPPER.entitiesToDtos(entityList);
        log.info("ActionLog.createUser.end");
        return dtoList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("ActionLog.removeNote.start: username {}", username);
        UserEntity user = repository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("exception.ms-notes.user-not-found"));
        log.info("ActionLog.removeNote.end: username {}", username);
        return new User(user.getUsername(), user.getPassword(), new ArrayList<>()
        );
    }
}
