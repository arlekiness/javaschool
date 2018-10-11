package ru.javasch.metro.mock;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.javasch.metro.dao.interfaces.StationDAO;
import ru.javasch.metro.model.Branch;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Status;
import ru.javasch.metro.service.implementations.StationServiceImpl;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StationServiceTest {

    private Station station;

    @Mock
    private StationDAO stationDAO;

    @InjectMocks
    private StationServiceImpl stationService;

    @Before
    public void setUp() {
        station = new Station();
        station.setId(27);
        station.setName("Karasevo");
        Status status = new Status();
        status.setId(1L);
        status.setStatusName("WORKED");
        station.setStatus(status);
        Branch branch = new Branch();
        branch.setColor("BLUE");
        branch.setId(2L);
        station.setBranch(branch);
        station.setNumberOnBranch(2);
    }

    @Test
    public void getAllStations() {
        when(stationDAO.getAll()).thenReturn(new ArrayList<>());
        stationService.getAllStations();
        verify(stationDAO).getAll();
    }

    @Test
    public void getById() {
        when(stationDAO.getById(27)).thenReturn(new Station());
        stationService.getById(27);
        verify(stationDAO).findStationById(27);
    }

    @Test
    public void getByName() {
        when(stationDAO.findByName("Karasevo")).thenReturn(new Station());
        stationService.findByName("Karasevo");
        verify(stationDAO).findByName("Karasevo");
    }
}