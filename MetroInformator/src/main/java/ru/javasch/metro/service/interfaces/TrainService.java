package ru.javasch.metro.service.interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.model.Train;

@Service
public interface TrainService {
    public Long add (String trainName);
    public void delete (Long Id);
    public Train findById(Long Id);
//    public String formTrainName (String stationName,  String dateTime);
}
