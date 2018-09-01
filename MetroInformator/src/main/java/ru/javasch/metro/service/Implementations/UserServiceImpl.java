package ru.javasch.metro.service.Implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.RoleDAO;
import ru.javasch.metro.DAO.Interfaces.UserDAO;
import ru.javasch.metro.DTO.UserDTO;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.model.Role;
import ru.javasch.metro.model.User;
import ru.javasch.metro.service.Interfaces.RoleService;
import ru.javasch.metro.service.Interfaces.UserService;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
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
    private SecureService secureService;

    @Autowired
    private ModelMapper modelMapper;

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
    public UserDTO findAuthenticatedUserDTO() {
        System.out.println("findAuthenticatedUserDTO");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userDAO.findUserByEmail(userName);
        System.out.println(user);
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    @Transactional
    public User findAuthenticatedUser() {
        String login = secureService.getAuthentication().getName();
        return userDAO.findUserByEmail(login);
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

//    @Override
//    @Transactional
//    public void updateProfile(UserDTO userDTO) throws ParseException, BusinessLogicException {
//        User user = findAuthenticatedUser();
//        Date birthDay = Utils.parseToDate(userDTO.getBirthDay());
//
//        if (!birthDay.before(new Date()))
//            throw new BusinessLogicException(ErrorCode.WRONG_BIRTHDAY.getMessage());
//
//        if (findUserByEmail(userDTO.getLogin()) != null && !findUserByEmail(userDTO.getLogin()).getLogin().equals(user.getLogin()))
//            throw new BusinessLogicException(ErrorCode.WRONG_LOGIN.getMessage());
//
//        user.setFirstName(userDTO.getFirstName());
//        user.setLastName(userDTO.getLastName());
//        user.setLogin(userDTO.getLogin());
//        user.setBirthDay(userDTO.getBirthDay());
//        user.setSex(userDTO.getSex());
//        userDAO.updateProfile(user);
//    }

    @Override
    @Transactional
    public void registration(String firstName, String lastName, String login, String password) throws IOException, BusinessLogicException, MessagingException {
        if (firstName == null)
            throw new BusinessLogicException(ErrorCode.NULL_ELEMENTS.getMessage());

        if (findUserByEmail(login) != null)
            throw new BusinessLogicException(ErrorCode.USER_ALREADY_EXIST.getMessage());

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
        add(user);
    }
}
