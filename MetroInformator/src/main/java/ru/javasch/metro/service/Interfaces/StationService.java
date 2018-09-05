package ru.javasch.metro.service.Interfaces;

import ru.javasch.metro.model.Station;

import java.util.List;
import java.util.Set;

public interface StationService {
    public Station findByName(String stationName);
    List<Station> getAllStations();
    public Set<Station> getAllTransitionalByName(String name);
    public List<Station> getAllStationsBeetweenTwoPoints(String beginName, String endName);
    public List<Station> getAllStationOnBranch (String stationName);

    public void closeStation (String stationName);
    public void openStation (String stationName);
}
