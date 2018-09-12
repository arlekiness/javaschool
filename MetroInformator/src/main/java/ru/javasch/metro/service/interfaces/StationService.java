package ru.javasch.metro.service.interfaces;

import ru.javasch.metro.model.Station;

import java.util.List;

public interface StationService {
    Station findByName(String stationName);
    List<Station> getAllStations();;
    List<Station> getAllStationsBeetweenTwoPoints(String beginName, String endName);
    List<Station> getAllStationOnBranch (String stationName);
    List<List<Station>> formSegments(List<Station> stations);
    void closeStation (String stationName);
    void openStation (String stationName);
    void checkSegments(List<List<Station>> segments);
    List<List<Station>> findPathSegments(List<List<Station>> segments);
}
