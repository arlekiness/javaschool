package ru.javasch.metro.DAO.Interfaces;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.model.Station;

@Repository
public interface StationDAO<E extends Station> extends GenericDAO<E> {

    Station findByName(String name);
}
