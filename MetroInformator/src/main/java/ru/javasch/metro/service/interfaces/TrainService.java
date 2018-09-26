package ru.javasch.metro.service.interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.model.Train;

import java.util.List;

@Service
public interface TrainService {
    Long add(String trainName);
    void delete(Long Id);
    Train findById(Long Id);
    List<Train> getAllTrains();
    List<Train> getTrainsByPage(int pageNum);
}
