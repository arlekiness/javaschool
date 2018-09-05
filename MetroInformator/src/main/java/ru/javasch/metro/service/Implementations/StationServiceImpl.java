package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.StatusDAO;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Status;
import ru.javasch.metro.service.Interfaces.StationService;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDAO stationDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Override
    @Transactional
    public Station findByName(String stationName) {return stationDAO.findByName(stationName);}

    @Override
    @Transactional
    public List<Station> getAllStations() {
        return stationDAO.getAll();
    }

    @Override
    @Transactional
    public Set<Station> getAllTransitionalByName(String name) {
        Set<Station> sst = stationDAO.findByName(name).getTransitions();
        return sst;
    }

    @Override
    @Transactional
    public List<Station> getAllStationsBeetweenTwoPoints(String beginName, String endName) {
        Station beginStation = stationDAO.findByName(beginName);
        Station endStation = stationDAO.findByName(endName);
        List<Station> stations = stationDAO.findAllStationBetweenBeginAndEndPoint(beginStation, endStation);
        return stations;
    }

    @Override
    @Transactional
    public List<Station> getAllStationOnBranch (String stationName) {
        Station station = stationDAO.findByName(stationName);
        List<Station> stations = stationDAO.getAllStationOnBranch(station);
        return stations;
    }

    @Override
    @Transactional
    public void closeStation (String stationName) {
        Station station = stationDAO.findByName(stationName);
        Status status = statusDAO.getCloseStatus();
        station.setStatus(status);
    }

    @Override
    @Transactional
    public void openStation (String stationName) {
        Station station = stationDAO.findByName(stationName);
        Status status = statusDAO.getWorkStatus();
        station.setStatus(status);
    }
}
