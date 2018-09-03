package ru.javasch.metro.service.Interfaces;

import ru.javasch.metro.model.Station;

import java.util.List;
import java.util.Set;

public interface StationService {
    List<Station> getAllStations();
    public void add(Station station);
    public void delete(String name);
    public Set<Station> getAllTransitionalByName(String name);
    public List<Station> getAllStationsBeetweenTwoPoints(String beginName, String endName);
}
