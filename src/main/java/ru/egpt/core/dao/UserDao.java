package ru.egpt.core.dao;

import ru.egpt.core.entity.User;

public interface UserDao {
    User getByUsername(String username);
}
