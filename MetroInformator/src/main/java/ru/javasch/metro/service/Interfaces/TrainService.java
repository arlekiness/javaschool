package ru.javasch.metro.service.Interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.model.Train;

@Service
public interface TrainService {
    public Train findByName(String trainName);
    public void add (String trainName);
    public void delete (String trainName);
}
