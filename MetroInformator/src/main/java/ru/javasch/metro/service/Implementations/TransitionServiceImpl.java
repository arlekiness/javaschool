package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.TransitionDAO;
import ru.javasch.metro.model.Branch;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Transition;
import ru.javasch.metro.service.Interfaces.TransitionService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TransitionServiceImpl implements TransitionService {
     @Autowired
     TransitionDAO transitionDAO;

    @Autowired
    StationDAO stationDAO;

    @Override
    @Transactional
    public List<Transition> getTransitionsByStationName (String stationName) {
        Station station = stationDAO.findByName(stationName);
        List<Transition> transition = transitionDAO.getTransitionsByStation(station);
        List<Transition> needDeleting = new ArrayList<>();
        for (Transition t : transition)
            if (t.getStatus().getStatusName().equals("CLOSED"))
                needDeleting.add(t);
        transition.removeAll(needDeleting);
        return transition;
    }


}
