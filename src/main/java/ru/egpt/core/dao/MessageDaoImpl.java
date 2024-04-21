package ru.egpt.core.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.egpt.core.entity.Message;
import ru.egpt.core.repository.MessageRepository;

@Component
@RequiredArgsConstructor
public class MessageDaoImpl implements MessageDao {

    private final MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }
}
