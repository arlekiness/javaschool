package ru.javasch.metro.integrations;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.javasch.metro.configuration.*;
import ru.javasch.metro.dao.interfaces.GraphDAO;
import ru.javasch.metro.service.interfaces.StationService;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        HibernateConfiguration.class,
        SecurityConfiguration.class,
        SecurityInitializer.class,
        ViewConfiguration.class,
        ViewInitializer.class})
@WebAppConfiguration
public class GraphServiceJTest {
    @Autowired
    GraphDAO graphDAO;

    @Autowired
    StationService stationService;

    @Test
    @Transactional
    public void getAll() {
        Assert.assertNotNull(graphDAO.getAll());
    }

    @Test
    @Transactional
    public void getAllNodes() {
        Assert.assertNotNull(graphDAO.getAllNodes(stationService.findByName("Kupchino")));
    }
}
