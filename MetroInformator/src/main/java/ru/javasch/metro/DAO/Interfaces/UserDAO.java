package ru.javasch.metro.DAO.Interfaces;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.model.User;

@Repository
public interface UserDAO<E extends User> extends GenericDAO<E> {
    void updateProfile(User user);
    User findUserByEmail(String login);
}
