package ru.javasch.metro.service.Interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Transition;

import java.util.List;

@Service
public interface PathFinderService {
    public Transition hasEqualElement (List<Transition> transition_1, List<Transition> transition_2);
    public List<List<Station>> formPathForTwoStations (Station fromStation, Station toStation);
    public List<List<Station>> formPathForThreeStations (Station fromStation, Transition cross, Station toStation);
    public void findPathBeetweenTwoStations (String stationNameFrom, String stationNameTo);
    public List<Transition> transitionForBranch (String stationName, List<Transition> allTransition);
}
