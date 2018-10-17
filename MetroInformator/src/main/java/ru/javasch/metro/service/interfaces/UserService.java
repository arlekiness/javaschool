package ru.javasch.metro.service.interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

@Service
public interface UserService {
    void add(User user);

    void remove(User user);

    List<User> getUsers();

    User findAuthenticatedUser();

    User findUserByEmail(String email);

    void update(User user);

    void registration(String firstName, String lastName, String login, String password, String phone) throws IOException, MessagingException;

    String getUserId();
}
