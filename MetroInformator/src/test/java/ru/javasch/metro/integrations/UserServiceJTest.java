package ru.javasch.metro.integrations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.javasch.metro.configuration.*;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.User;
import ru.javasch.metro.service.interfaces.UserService;

import javax.mail.MessagingException;
import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        HibernateConfiguration.class,
        SecurityConfiguration.class,
        SecurityInitializer.class,
        ViewConfiguration.class,
        ViewInitializer.class})
@WebAppConfiguration
public class UserServiceJTest {
    @Autowired
    private UserService userService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void getUsers() {
        Assert.assertNotNull(userService.getUsers());
    }

    @Test
    public void findUserByEmail() {
        Assert.assertNotNull(userService.findUserByEmail("volpert13@gmail.com"));
    }

    @Test
    public void registration() throws IOException, MessagingException {
        thrown.expect(RuntimeBusinessLogicException.class);
        userService.registration("", "", "", "");
    }
}
