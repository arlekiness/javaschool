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

    UserDTO findAuthenticatedUserDTO();

    User findAuthenticatedUser();

    User findUserByEmail(String email);

    void update(User user);

    void updateProfile(UserDTO userDTO) throws ParseException, BusinessLogicException;

    void registration(UserDTO userDTO) throws IOException, BusinessLogicException, MessagingException;
}
