package ru.javasch.metro.service.Implementations;

import javafx.util.Pair;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.DAO.Interfaces.GraphDAO;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Graph;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Status;
import ru.javasch.metro.service.Interfaces.PathFinderService;
import ru.javasch.metro.service.Interfaces.StationService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class PathFinderServiceImpl implements PathFinderService {
    @Autowired
    StationService stationService;


    @Autowired
    GraphDAO graphDAO;

    @Autowired
    StationDAO stationDAO;


    @Override
    @Transactional
    public List<Station> pathFinder(String stationBegin, String stationEnd) {
        List<Graph> st1 = graphDAO.getAllNodes(stationService.findByName(stationBegin));
        List<Graph> st2 = graphDAO.getAllNodes(stationService.findByName(stationEnd));

        int closed1 = 0;
        int closed2 = 0;

        for (Graph r : st1)
            if (r.getOldWeight() != 100000)
                closed1++;

        for (Graph r : st2)
            if (r.getOldWeight() != 100000)
                closed2++;

        System.out.println(st1.size() + " " + st2.size() + " " + closed1 + closed2);

        if (closed1 > 0 || closed2 > 0)
            throw new RuntimeBusinessLogicException("Something go wrong. Some Stations are closed");

        int indexBeg = stationService.findByName(stationBegin).getId() - 1;
        int indexEnd = stationService.findByName(stationEnd).getId() - 1;
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

            while (true) {
                List<Integer> availTrans = new ArrayList<>();
                for (int i = 0; i < 69; i++) {
                    if (graphArray[interIndex][i] != 100000)
                        availTrans.add(i);
                }

                List<Pair<Integer, Integer>> interSt = new ArrayList<>();
                for (int j = 0; j < availTrans.size() - 1; j++) {
                    for (int k = 0; k < 69; k++) {
                        int l = availTrans.get(j);
                        if (graphArray[k][l] == 50)
                            interSt.add(new Pair<>(k, l));
                    }
                }
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
                transCount++;
                if (transCount > 10)
                    throw new RuntimeBusinessLogicException("All Transition Stations is Closed. Can't find the way");
            }
            path.add(indexEnd);
            /**  */

        }

        List<Station> stations = new ArrayList<>();
        for (int id : path) {
            Station st = stationDAO.findStationById(id + 1);
            stations.add(st);
        }



        return stations;
    }
}
