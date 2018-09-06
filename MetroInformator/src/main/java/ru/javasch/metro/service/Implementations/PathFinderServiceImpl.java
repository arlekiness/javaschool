package ru.javasch.metro.service.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.TransitionDAO;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Branch;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Transition;
import ru.javasch.metro.service.Interfaces.PathFinderService;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TransitionService;

import javax.transaction.Transaction;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PathFinderServiceImpl implements PathFinderService {
    @Autowired
    StationService stationService;

    @Autowired
    TransitionService transitionService;

/***/
    @Override
    @Transactional
    public Transition hasEqualElement (List<Transition> transition_1, List<Transition> transition_2) {
        for (Transition t1 : transition_1)
            for (Transition t2 : transition_2)
                if (t1.equals(t2))
                    return t1;
        return null;
    }

    /***/
    @Override
    @Transactional
    public List<List<Station>> formPathForTwoStations (Station fromStation, Station toStation) {
        List<List<Station>> listStation= new ArrayList<>();
        List<Station> segment = new ArrayList<>();
        segment.add(fromStation);
        segment.add(toStation);
        listStation.add(segment);
        return listStation;
    }
    /***/
    @Override
    @Transactional
    public List<List<Station>> formPathForThreeStations (Station fromStation, Transition cross, Station toStation) {
        List<List<Station>> listStation= new ArrayList<>();
        Station stationCross1 = cross.getStation_1();
        Station stationCross2 = cross.getStation_2();
        List<Station> segment1 = new ArrayList<>();
        List<Station> segment2 = new ArrayList<>();
        if (fromStation.getBranch().equals(stationCross1.getBranch())) {
            segment1.add(fromStation);
            segment1.add(stationCross1);
            listStation.add(segment1);
            segment2.add(stationCross2);
            segment2.add(toStation);
            listStation.add(segment2);
        } else {
            segment1.add(fromStation);
            segment1.add(stationCross2);
            listStation.add(segment1);
            segment2.add(stationCross1);
            segment2.add(toStation);
            listStation.add(segment2);
        }
        return listStation;
    }
    /***/
    @Override
    @Transactional
    public List<Transition> transitionForBranch (String stationName, List<Transition> allTransition) {
        Station station = stationService.findByName(stationName);
        Branch branch = station.getBranch();
        List<Transition> transition = new ArrayList<>();
        for (Transition t : allTransition)
            if (t.getStation_1().getBranch().getColor().equals(branch.getColor()) || t.getStation_2().getBranch().getColor().equals(branch.getColor()))
                transition.add(t);
        return transition;
    }
    /***/
    @Override
    @Transactional
    public void findPathBeetweenTwoStations (String stationNameFrom, String stationNameTo) {
        Station fromStation = stationService.findByName(stationNameFrom);
        Station toStation = stationService.findByName(stationNameTo);
        List<Station> branch1= stationService.getAllStationOnBranch(stationNameFrom);
        List<Station> branch2= stationService.getAllStationOnBranch(stationNameTo);

        List<List<Station>> listStation = new ArrayList<>();

        if (fromStation.getBranch().equals(toStation.getBranch())) {
            listStation.addAll(formPathForTwoStations (fromStation, toStation));

        } else {
            List<Transition> transitions_1 = new ArrayList<>();
            List<Transition> transitions_2 = new ArrayList<>();

            for (Station st : branch1) {
                List<Transition> trProm = transitionService.getTransitionsByStationName(st.getName());
                transitions_1.addAll(trProm);
            }
            for (Station st : branch2) {
                List<Transition> trProm = transitionService.getTransitionsByStationName(st.getName());
                transitions_2.addAll(trProm);
            }

            if(transitions_1.size() == 0 || transitions_2.size() == 0)
                throw new RuntimeBusinessLogicException("Needed Transition Stations closed");

            transitions_2.removeAll(transitions_1);
            transitions_1.addAll(transitions_2);
            List<Transition> transForFrom = this.transitionForBranch(stationNameFrom, transitions_1);
            List<Transition> transForTo = this.transitionForBranch(stationNameTo, transitions_1);
            //===================================

            //====================================

            Transition cross = this.hasEqualElement(transForFrom, transForTo);
            if (cross != null) {
                listStation.addAll(formPathForThreeStations(fromStation, cross, toStation));
            } else {
                if (Math.abs(fromStation.getNumberOnBranch() - branch1.size()) < branch1.size()/2)
                    Collections.reverse(transForFrom);
//                for (Transition t : transForFrom) {
//                    System.out.println(t.getStation_1() + " " + t.getStation_2());
//                }
//                System.out.println("==============");
//                for (Transition t : transForTo) {
//                    System.out.println(t.getStation_1() + " " + t.getStation_2());
//                }
//                System.out.println("==============");
                for (Transition t : transForFrom) {
                    Station transition;
                    if (t.getStation_1().getBranch().equals(fromStation.getBranch()))
                        transition = t.getStation_1();
                    else
                        transition = t.getStation_2();
                }

            }
        }

        for (List<Station> st : listStation) {
            for (Station s1 : st)
                System.out.print(s1.getName() + " ");
            System.out.println();
        }
        System.out.println("*****************");




    }


}
