package ru.javasch.metro.DAO.Interfaces;

import org.hibernate.NonUniqueResultException;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;

@Repository
public interface TrainDAO<E extends Train> extends GenericDAO<E> {
    Train findByName(String trainName);
}
