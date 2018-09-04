package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.TrainDAO;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.Interfaces.TrainService;

import javax.transaction.Transactional;

@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    TrainDAO trainDAO;

    @Override
    @Transactional
    public Train findByName(String trainName) {
        return trainDAO.findByName(trainName);
    }
}
