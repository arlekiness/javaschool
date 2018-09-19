package ru.javasch.metro.service.interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.model.Station;

import java.util.List;

@Service
public interface GraphService {
    public List<Station> changeWeight (String stationName);
}
