package ru.javasch.metro.integrations;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.javasch.metro.configuration.*;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.service.interfaces.StationService;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        HibernateConfiguration.class,
        SecurityConfiguration.class,
        SecurityInitializer.class,
        ViewConfiguration.class,
        ViewInitializer.class})
@WebAppConfiguration
public class StationServiceJTest {

    @Autowired
    StationService stationService;

    @Test
    public void findByName() {
        Assert.assertTrue(stationService.findByName("Devyatkino").getId() == 1);
    }

    @Test
    public void findByNameExc() {
        try {
            stationService.findByName("Karasevo");
        } catch (Exception e) {
            if (e instanceof RuntimeBusinessLogicException) {
                Assert.assertEquals("Such station doesn't exists", ((RuntimeBusinessLogicException) e).getError());
            }
        }
    }

    @Test
    public void getAllStations() {
        Assert.assertTrue(stationService.getAllStations().size() == 69);
    }

    @Test
    public void getAllStationsBeetweenTwoPoints() {
        Assert.assertTrue(stationService.getAllStationsBeetweenTwoPoints("Chkalovskaya", "Admiralteyskaya").size() == 2);
    }

    @Test
    public void getAllStationOnBranch() {
        Assert.assertTrue(stationService.getAllStationOnBranch("Devyatkino").size() == 19);
    }

    @Test
    public void getStationsBetweenIDs() {
        Assert.assertTrue(stationService.getStationsBetweenIDs(1, 19).size() == 19);
    }

    @Test
    public void getAllDTOs() {
        Assert.assertTrue(stationService.getAll().size() == 69);
    }


}
