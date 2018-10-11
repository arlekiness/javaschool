package ru.javasch.metro.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.javasch.metro.dao.interfaces.TrainDAO;
import ru.javasch.metro.model.Status;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.implementations.TrainServiceImpl;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrainServiceTest {
    private Train train;

    @Mock
    private TrainDAO trainDAO;

    @InjectMocks
    private TrainServiceImpl trainService;

    @Before
    public void setUp() {
        train = new Train();
        train.setTrainName("T-1000");
        train.setId(1000L);
        train.setCapacity(6);
        Status status = new Status();
        status.setId(1L);
        status.setStatusName("WORKED");
        train.setStatus(status);
    }

    @Test
    public void getAll() {
        when(trainDAO.getAll()).thenReturn(new ArrayList<>());
        trainService.getAllTrains();
        verify(trainDAO).getAll();
    }

    @Test
    public void getById() {
        when(trainDAO.getById(1000L)).thenReturn(new Train());
        trainService.findById(1000L);
        verify(trainDAO).getById(1000L);
    }
}