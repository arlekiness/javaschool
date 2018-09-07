package ru.javasch.metro.service.Interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Transition;

import java.util.List;

@Service
public interface PathFinderService {
    public List<Station> pathFinder(String stationBegin, String stationEnd);
}
