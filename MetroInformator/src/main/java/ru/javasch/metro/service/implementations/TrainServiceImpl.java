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
import ru.javasch.metro.utils.Utils;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
@Log4j
public class TrainServiceImpl implements TrainService {
    @Autowired
    private TrainDAO trainDAO;

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private MessageQueueService messageQueueService;

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
    public void delete(Long Id) throws IOException, TimeoutException {
        Date now = new Date();
        Calendar calNow = Calendar.getInstance();
        calNow.setTime(now);
        Utils.setHMSMfieldsInZero(calNow);
        now = calNow.getTime();
        Train train = (Train) trainDAO.getById(Id);
        List<Schedule> schedules = scheduleService.getByTrain(train);
        List<Long> deletedSchedulesIds = new ArrayList<>();
        for (Schedule sch : schedules) {
            Date fromSch = sch.getDateDeparture();
            Calendar calSch = Calendar.getInstance();
            calSch.setTime(fromSch);
            Utils.setHMSMfieldsInZero(calSch);
            fromSch = calSch.getTime();
            if (now.equals(fromSch))
                deletedSchedulesIds.add(sch.getId());
            scheduleService.deletePastSchedules(sch);
        }

        log.info("TRAIN " + train.getTrainName() + " REMOVED");
        trainDAO.delete(train);
        messageQueueService.produceMsg("deletedtrain schedules " + deletedSchedulesIds);
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
