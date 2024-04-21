package ru.egpt.core.dao;

import ru.egpt.core.entity.Message;

public interface MessageDao {
    Message save(Message message);
}
