package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.service.Interfaces.StationService;
import javax.transaction.Transactional;

import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDAO stationDAO;

    @Override
    @Transactional
    public List<Station> getAllStations() {
        return stationDAO.getAll();
    }

    @Override
    @Transactional
    public void add(Station station) {
        stationDAO.add(station);
    }

    @Override
    @Transactional
    public void delete(String name) {
        System.out.println("Here " + name);
        Station station = stationDAO.findByName(name);
        stationDAO.delete(station);
    }
}
