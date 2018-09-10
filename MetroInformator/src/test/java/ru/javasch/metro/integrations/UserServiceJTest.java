package ru.javasch.metro.integrations;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.javasch.metro.configuration.*;
import ru.javasch.metro.service.Interfaces.UserService;

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

    @Test
    public void getUsers() {
        Assert.assertNotNull(userService.getUsers());
    }

    @Test
    public void findUserByEmail() {
        Assert.assertNotNull(userService.findUserByEmail("volpert13@gmail.com"));
    }
}
