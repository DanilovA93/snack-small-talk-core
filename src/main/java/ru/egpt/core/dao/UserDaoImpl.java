package ru.egpt.core.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.egpt.core.entity.User;
import ru.egpt.core.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final UserRepository userRepository;

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Пользователь " + username + " не найден"));
    }
}
