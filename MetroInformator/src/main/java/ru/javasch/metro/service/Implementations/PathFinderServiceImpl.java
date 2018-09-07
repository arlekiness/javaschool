package ru.javasch.metro.service.Implementations;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.GraphDAO;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.TransitionDAO;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Branch;
import ru.javasch.metro.model.Graph;
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

    @Autowired
    GraphDAO graphDAO;

    @Autowired
    StationDAO stationDAO;


    @Override
    @Transactional
    public List<Station> pathFinder(String stationBegin, String stationEnd) {
        int indexBeg = stationService.findByName(stationBegin).getId() - 1;
        int indexEnd = stationService.findByName(stationEnd).getId() - 1;
        System.out.println(indexBeg + " " + indexEnd);
        int[][] graphArray = new int[69][69];
        List<Integer> path = new ArrayList<>();
        List<Graph> graph = graphDAO.getAll();
        path.add(indexBeg);
        int index = 0;
        for (int i = 0; i < 69; i++) {
            for (int j = 0; j < 69; j++) {
                graphArray[i][j] = graph.get(index).getWeight();
                index++;
            }
        }
        int transCount = 0;
        int interIndex = indexBeg;
        Pair<Integer, Integer> notIncluded = null;
        if (graphArray[indexEnd][indexBeg] != 100000) {
            path.add(indexEnd);
        } else {
        /**               */
//            if (indexBeg > indexEnd) {
                while (true) {
                    List<Integer> availTrans = new ArrayList<>();
                    for (int i = 0; i < 69; i++) {
                        if (graphArray[interIndex][i] != 100000)
                            availTrans.add(i);
                    }
                    System.out.println(availTrans);

                    List<Pair<Integer, Integer>> interSt = new ArrayList<>();
//                    if (indexEnd > indexBeg) {
                        for (int j = 0; j < availTrans.size() - 1; j++) {
                            for (int k = 0; k < 69; k++) {
                                int l = availTrans.get(j);
                                if (graphArray[k][l] == 50)
                                    interSt.add(new Pair<>(k, l));
                            }
                        }
                        System.out.println(interSt);
                        interSt.remove(notIncluded);
                        int bestTransition = 10000;
                        int endDest = 0;
                        for (Pair<Integer, Integer> pair : interSt) {
                            if (Math.abs(indexEnd - pair.getKey()) < Math.abs(indexEnd - bestTransition)) {
                                notIncluded = pair;
                                bestTransition = pair.getKey();
                                endDest = pair.getValue();
                            }
                        }
                        path.add(endDest);
                        path.add(bestTransition);
                        if (graphArray[bestTransition][indexEnd] != 100000)
                            break;
                        interIndex = bestTransition;
//                    }

                }
                path.add(indexEnd);
                /**  */
//            } else {
        }

        List<Station> stations = new ArrayList<>();
        for (int id : path) {
            Station st = stationDAO.findStationById(id + 1);
            stations.add(st);
        }

        for (Station st : stations)
            System.out.print(st.getName() + "-->");

        System.out.println("**********");

        return stations;
    }
}
