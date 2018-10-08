package ru.javasch.metro.service.interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.model.Train;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public interface TrainService {
    Long add(String trainName);

    void delete(Long Id) throws IOException, TimeoutException;

    Train findById(Long Id);

    List<Train> getAllTrains();

    List<Train> getTrainsByPage(int pageNum);
}
