package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.Train;

import java.util.List;

public interface TrainDAO<E extends Train> extends GenericDAO<E> {
    Train findByName(String trainName);

    List<Train> getTrainByPage(int pageNum);
}
