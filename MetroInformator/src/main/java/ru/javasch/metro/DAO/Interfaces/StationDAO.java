package ru.javasch.metro.DAO.Interfaces;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.model.Station;

import java.util.List;

@Repository
public interface StationDAO<E extends Station> extends GenericDAO<E> {

    Station findByName(String name);

    public List<Station> getAllStationOnBranch(Station station);

    public List<Station> findAllStationBetweenBeginAndEndPoint (Station begin, Station end);
}
