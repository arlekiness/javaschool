package ru.javasch.metro.service.Interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.model.Train;

@Service
public interface TrainService {
    public Train findByName(String trainName);
    public Long add (String trainName);
    public void delete (Long Id);
    public Train findById(Long Id);
//    public String formTrainName (String stationName,  String dateTime);
}
