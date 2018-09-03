package ru.javasch.metro.service.Interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.DTO.UserDTO;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.model.User;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public interface UserService {

    void add(User user);

    void remove(User user);

    List<User> getUsers();

    User findAuthenticatedUser();

    User findUserByEmail(String email);

    void update(User user);


    void registration(String firstName, String lastName, String login, String password) throws IOException, BusinessLogicException, MessagingException;

    String getUserId();
}
