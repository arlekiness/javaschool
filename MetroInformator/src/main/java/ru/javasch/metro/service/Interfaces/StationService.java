package ru.javasch.metro.service.Interfaces;

import ru.javasch.metro.model.Station;

import java.util.List;
import java.util.Set;

public interface StationService {
    public Station findByName(String stationName);
    List<Station> getAllStations();;
    public List<Station> getAllStationsBeetweenTwoPoints(String beginName, String endName);
    public List<Station> getAllStationOnBranch (String stationName);
    public List<List<Station>> formSegments(List<Station> stations);
    public void closeStation (String stationName);
    public void openStation (String stationName);
    public void checkSegments(List<List<Station>> segments);
    public List<List<Station>> findPathSegments(List<List<Station>> segments);
}
