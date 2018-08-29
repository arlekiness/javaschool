package ru.javasch.metro.service.Interfaces;

import ru.javasch.metro.model.Station;

import java.util.List;

public interface StationService {
    List<Station> getAllStations();
    public void add(Station station);
    public void delete(String name);
}
