package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.Station;

import java.util.List;

public interface StationDAO<E extends Station> extends GenericDAO<E> {

    Station findByName(String name);

    public List<Station> getAllStationOnBranch(Station station);

    public List<Station> findAllStationBetweenBeginAndEndPoint (Station begin, Station end);

    public Station findStationById(Integer id);
}
