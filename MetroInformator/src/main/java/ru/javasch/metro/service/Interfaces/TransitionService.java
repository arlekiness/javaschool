package ru.javasch.metro.service.Interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.model.Branch;
import ru.javasch.metro.model.Transition;

import java.util.List;

@Service
public interface TransitionService {
    public List<Transition> getTransitionsByStationName (String stationName);
}
