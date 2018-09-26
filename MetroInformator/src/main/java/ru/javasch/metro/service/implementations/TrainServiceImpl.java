package ru.javasch.metro.service.implementations;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.StatusDAO;
import ru.javasch.metro.dao.interfaces.TrainDAO;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Status;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.TrainService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Log4j
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Override
    @Transactional
    public Train findById(Long Id) {
        return (Train) trainDAO.getById(Id);
    }

    @Override
    @Transactional
    public Long add(String trainName) {
        Train train = trainDAO.findByName(trainName);
        if (train == null) {
            train = new Train();
            train.setTrainName(trainName);
            Status status = statusDAO.getWorkStatus();
            train.setTrainName(trainName);
            train.setCapacity(6);
            train.setStatus(status);
            trainDAO.add(train);
            log.info("TRAIN ADDED");
            return train.getId();
        } else {
            throw new RuntimeBusinessLogicException(ErrorCode.TRAIN_EXIST);
        }
    }

    @Override
    @Transactional
    public void delete(Long Id) {
        Train train = (Train) trainDAO.getById(Id);
        log.info("TRAIN " + train.getTrainName() + " REMOVED");
        trainDAO.delete(train);
    }

    @Override
    @Transactional
    public List<Train> getAllTrains() {
        return (List<Train>) trainDAO.getAll();
    }

    @Override
    @Transactional
    public List<Train> getTrainsByPage(int pageNum) { return (List<Train>) trainDAO.getTrainByPage(pageNum); }
}
