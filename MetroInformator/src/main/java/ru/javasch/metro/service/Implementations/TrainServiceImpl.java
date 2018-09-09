package ru.javasch.metro.service.Implementations;

import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javasch.metro.DAO.Interfaces.StatusDAO;
import ru.javasch.metro.DAO.Interfaces.TrainDAO;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.model.Branch;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Status;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TrainService;

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
    public Train findByName(String trainName) {
        return trainDAO.findByName(trainName);
    }

    @Override
    @Transactional
    public Train findById(Long Id) { return (Train)trainDAO.getById(Id); }

    @Override
    @Transactional
    public Long add (String trainName) {
            Train train = new Train();
            train.setTrainName(trainName);
            Status status = statusDAO.getWorkStatus();
            train.setTrainName(trainName);
            train.setCapacity(6);
            train.setStatus(status);
            trainDAO.add(train);
            return train.getId();
    }

    @Override
    @Transactional
    public void delete (Long Id) {
        Train train = (Train)trainDAO.getById(Id);
        trainDAO.delete(train);
    }

//    @Override
//    @Transactional
//    public String formTrainName (String stationName,  String dateTime) {
//        Station st = stationService.findByName(stationName);
//        Branch branch = st.getBranch();
//        StringBuilder str = new StringBuilder("T");
//        str.append(branch.getId()).append("-" + st.getName() + "-");
//
//        for (int i = 0; i < dateTime.length(); i++) {
//            char c = dateTime.charAt(i);
//            if (Character.isDigit(c))
//                str.append(c);
//        }
//        return new String(str);
//
//    }
}
