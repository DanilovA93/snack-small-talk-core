package ru.egpt.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.egpt.core.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
