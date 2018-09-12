package ru.javasch.metro.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.UserDAO;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Message;
import ru.javasch.metro.model.Role;
import ru.javasch.metro.model.User;
import ru.javasch.metro.service.interfaces.RoleService;
import ru.javasch.metro.service.interfaces.UserService;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MailService mailService;


    @Autowired
    private SecureService secureService;

    @Override
    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    @Transactional
    public void remove(User user) {
        userDAO.delete(user);
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        return userDAO.getAll();
    }

    @Override
    @Transactional
    public User findAuthenticatedUser() {
        String login = secureService.getAuthentication().getName();
        return userDAO.findUserByEmail(login);
    }

    @Override
    @Transactional
    public String getUserId() {
        String login = secureService.getAuthentication().getName();
        User user = userDAO.findUserByEmail(login);
        return user.getId().toString();
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void registration(String firstName, String lastName, String login, String password) throws IOException, MessagingException {
        if (firstName == "" || lastName == "" || login == "" || password == "")
            throw new RuntimeBusinessLogicException("One or two fields are empty");

        if (findUserByEmail(login) != null)
            throw new RuntimeBusinessLogicException("User already exist");

        Role role = roleService.getRole();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User();
        user.setLogin(login);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        user.setRoles(roleSet);
        Message message = Message.createWelcomeMessage(login);
        mailService.sendMimeMessage(message);
        add(user);
    }
}
