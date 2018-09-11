package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.User;

public interface UserDAO<E extends User> extends GenericDAO<E> {
    void updateProfile(User user);

    User findUserByEmail(String login);
}
