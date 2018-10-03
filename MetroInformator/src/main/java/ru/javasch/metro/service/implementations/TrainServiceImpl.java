package ru.javasch.metro.service.implementations;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.StatusDAO;
import ru.javasch.metro.dao.interfaces.TrainDAO;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Status;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.ScheduleService;
import ru.javasch.metro.service.interfaces.TrainService;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Log4j
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private ScheduleService scheduleService;

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
            log.info("EXCEPTION: " + ErrorCode.TRAIN_EXIST);
            throw new RuntimeBusinessLogicException(ErrorCode.TRAIN_EXIST);
        }
    }

    @Override
    @Transactional
    public void delete(Long Id) {
        Train train = (Train) trainDAO.getById(Id);
        List<Schedule> schedules = scheduleService.getByTrain(train);
        for (Schedule sch : schedules) {
            scheduleService.deletePastSchedules(sch);
        }
        log.info("TRAIN " + train.getTrainName() + " REMOVED");
        trainDAO.delete(train);
    }

    @Override
    @Transactional
    public List<Train> getAllTrains() {
        return (List<Train>) trainDAO.getAll();
    }

    /**HELPER METHOD FOR TRAIN PAGINATION
     * @see ru.javasch.metro.service.implementations.ControllerServiceImpl#trainPagination(int)
     * @param pageNum
     * @return
     */
    @Override
    @Transactional
    public List<Train> getTrainsByPage(int pageNum) {
        return (List<Train>) trainDAO.getTrainByPage(pageNum);
    }
}
