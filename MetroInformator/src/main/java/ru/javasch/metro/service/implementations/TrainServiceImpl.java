package ru.javasch.metro.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.StatusDAO;
import ru.javasch.metro.dao.interfaces.TrainDAO;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Status;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.StationService;
import ru.javasch.metro.service.interfaces.TrainService;

import javax.transaction.Transactional;

@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    TrainDAO trainDAO;

    @Autowired
    StatusDAO statusDAO;

    @Autowired
    StationService stationService;

    @Override
    @Transactional
    public Train findById(Long Id) { return (Train)trainDAO.getById(Id); }

    @Override
    @Transactional
    public Long add (String trainName) {
        Train train = trainDAO.findByName(trainName);
        if (train == null) {
            train = new Train();
            train.setTrainName(trainName);
            Status status = statusDAO.getWorkStatus();
            train.setTrainName(trainName);
            train.setCapacity(6);
            train.setStatus(status);
            trainDAO.add(train);
            return train.getId();
        } else {
            throw new RuntimeBusinessLogicException("Such train already exist");
        }
    }

    @Override
    @Transactional
    public void delete (Long Id) {
        Train train = (Train)trainDAO.getById(Id);
        trainDAO.delete(train);
    }
}
