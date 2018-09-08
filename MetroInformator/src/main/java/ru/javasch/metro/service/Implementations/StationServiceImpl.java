package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.StatusDAO;
import ru.javasch.metro.DAO.Interfaces.TransitionDAO;
import ru.javasch.metro.model.Graph;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Status;
import ru.javasch.metro.model.Transition;
import ru.javasch.metro.service.Interfaces.GraphService;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TransitionService;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationDAO stationDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private TransitionDAO transitionDAO;

    @Autowired
    private GraphService graphService;

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
        Status previousStatus = station.getStatus();
        Status status = statusDAO.getCloseStatus();
        station.setStatus(status);
        List<Transition> transition = transitionDAO.getTransitionsByStation(station);
        for (Transition t : transition)
            t.setStatus(status);
        if (!station.getStatus().getStatusName().equals(previousStatus.getStatusName()))
            graphService.changeWeight(stationName);
    }

    @Override
    @Transactional
    public void openStation (String stationName) {
        Station station = stationDAO.findByName(stationName);
        Status previousStatus = station.getStatus();
        Status status = statusDAO.getWorkStatus();
        station.setStatus(status);
        List<Transition> transition = transitionDAO.getTransitionsByStation(station);
        for (Transition t : transition)
            t.setStatus(status);
        if (!station.getStatus().getStatusName().equals(previousStatus.getStatusName()))
            graphService.changeWeight(stationName);
    }

    @Override
    @Transactional
    public List<List<Station>> formSegments(List<Station> stations) {
        List<List<Station>> segments = new ArrayList<>();
            for (int i = 1; i < stations.size(); i++) {
                List<Station> segment = new ArrayList<>();
                segment.add(stations.get(i - 1));
                segment.add(stations.get(i));
                segments.add(segment);
            }
        return segments;
    }

    @Override
    @Transactional
    public void checkSegments(List<List<Station>> segments) {
        Iterator it = segments.iterator();
        while (it.hasNext()) {
            List<Station> st = (List<Station>)it.next();
            if (st.get(0).equals(st.get(1)))
                it.remove();
        }
    }

    @Override
    @Transactional
    public List<List<Station>> findPathSegments(List<List<Station>> segments) {
        List<List<Station>> pathStation = new ArrayList<>();
        for (List<Station> st : segments) {
            if (st.get(0).getBranch().equals(st.get(1).getBranch()))
                pathStation.add(st);
        }
        return pathStation;
    }


}
