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
import ru.javasch.metro.service.interfaces.TrainService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        HibernateConfiguration.class,
        SecurityConfiguration.class,
        SecurityInitializer.class,
        ViewConfiguration.class,
        ViewInitializer.class})
@WebAppConfiguration
public class TrainServiceJTest {

    @Autowired
    TrainService trainService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @Transactional
    public void findById() {
        Assert.assertTrue(trainService.findById(76L).getTrainName().equals("T-76"));
    }

    @Test
    @Transactional
    public void add() throws IOException, TimeoutException {
        Long Id = trainService.add("T-999");
        Assert.assertTrue(trainService.findById(Id).getTrainName().equals("T-999"));
        trainService.delete(Id);
    }

    @Test
    @Transactional
    public void delete() throws IOException, TimeoutException {
        thrown.expect(NullPointerException.class);
        trainService.delete(1234L);
    }

    @Test
    @Transactional
    public void getAllTrains() {
        Assert.assertTrue(trainService.getAllTrains().size() == 172);
    }

    @Test
    @Transactional
    public void getTrainsbyPage() {
        Assert.assertTrue(trainService.getTrainsByPage(1).size() == 20);
    }
}
