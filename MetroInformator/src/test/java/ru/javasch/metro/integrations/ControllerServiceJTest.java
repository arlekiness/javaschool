package ru.javasch.metro.integrations;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.javasch.metro.configuration.*;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.ControllerService;
import ru.javasch.metro.utils.URLs;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        HibernateConfiguration.class,
        SecurityConfiguration.class,
        SecurityInitializer.class,
        ViewConfiguration.class,
        ViewInitializer.class})
@WebAppConfiguration
public class ControllerServiceJTest {
    @Autowired
    ControllerService controllerService;

    @Test
    public void stationPagination() {
        Map<String, Object> pag = controllerService.stationPagination(4);
        Assert.assertTrue(((List<Station>)pag.get("stations")).size() == 8);
    }

    @Test
    public void trainPagination() {
        Map<String, Object> pag = controllerService.trainPagination(1);
        Assert.assertTrue(((List<Train>)pag.get("trains")).size() == 20);
    }

    @Test
    public void switchHelper() {
        Assert.assertTrue(controllerService.stationSwitchHelper("BLUE").equals(URLs.REDIRECT_DASHSTATION + "/2"));
    }
}
