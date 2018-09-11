package ru.javasch.metro.integrations;


import org.junit.Assert;
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
import ru.javasch.metro.service.interfaces.ScheduleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        HibernateConfiguration.class,
        SecurityConfiguration.class,
        SecurityInitializer.class,
        ViewConfiguration.class,
        ViewInitializer.class})
@WebAppConfiguration
public class ScheduleServiceJTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    ScheduleService scheduleService;

    @Test
    public void getAllTrainsOnStation() {
        try {
            scheduleService.getAllTrainsOnStation("Chkalovskaya", "2018-09-09");
        } catch (Exception e) {
            if (e instanceof RuntimeBusinessLogicException) {
                Assert.assertEquals("There is no trains on that date on that station", ((RuntimeBusinessLogicException) e).getError());
            }
        }
    }

}
