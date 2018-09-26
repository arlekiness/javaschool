package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.Station;

import java.util.List;

public interface StationDAO<E extends Station> extends GenericDAO<E> {
    Station findByName(String name);

    List<Station> getAllStationOnBranch(Station station);

    List<Station> findAllStationBetweenBeginAndEndPoint(Station begin, Station end);

    Station findStationById(Integer id);

    List<Station> getStationsBetweenIDs(Integer stationBeginId, Integer stationEndId);
}
