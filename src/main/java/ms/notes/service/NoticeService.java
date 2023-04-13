package ms.notes.service;

import lombok.AllArgsConstructor;
import ms.notes.dao.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoticeService {

    private final NoticeRepository repository;
}
