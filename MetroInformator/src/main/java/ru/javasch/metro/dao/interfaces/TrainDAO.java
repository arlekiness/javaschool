package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.Train;

public interface TrainDAO<E extends Train> extends GenericDAO<E> {
    Train findByName(String trainName);
}
