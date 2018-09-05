package ru.javasch.metro.service.Implementations;

import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.StatusDAO;
import ru.javasch.metro.DAO.Interfaces.TrainDAO;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.model.Status;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.Interfaces.TrainService;

import javax.transaction.Transactional;

@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    TrainDAO trainDAO;

    @Autowired
    StatusDAO statusDAO;

    @Override
    @Transactional
    public Train findByName(String trainName) {
        return trainDAO.findByName(trainName);
    }

    @Override
    @Transactional
    public void add (String trainName) {
            trainDAO.findByName(trainName);
            Train train = new Train();
            Status status = statusDAO.getWorkStatus();
            train.setTrainName(trainName);
            train.setCapacity(6);
            train.setStatus(status);
            trainDAO.add(train);
    }

    @Override
    @Transactional
    public void delete (String trainName) {
        Train train = trainDAO.findByName(trainName);
        trainDAO.delete(train);
    }
}
