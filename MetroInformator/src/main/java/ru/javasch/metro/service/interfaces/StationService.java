package ru.javasch.metro.service.interfaces;

import ru.javasch.metro.dto.StationDTO;
import ru.javasch.metro.model.Station;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public interface StationService {
    Station findByName(String stationName);
    List<Station> getAllStations();
    List<Station> getAllStationsBeetweenTwoPoints(String beginName, String endName);
    List<Station> getAllStationOnBranch(String stationName);
    List<List<Station>> formSegments(List<Station> stations);
    void closeStation(String stationName) throws IOException, TimeoutException;
    void openStation(String stationName) throws IOException, TimeoutException;
    void checkSegments(List<List<Station>> segments);
    List<List<Station>> findPathSegments(List<List<Station>> segments);
    List<Station> getStationsBetweenIDs(Integer stationBeginId, Integer stationEndId);
    public List<StationDTO> getAll();
}
