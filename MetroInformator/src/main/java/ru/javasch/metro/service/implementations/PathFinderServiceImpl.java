package ru.javasch.metro.service.implementations;

import javafx.util.Pair;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.GraphDAO;
import ru.javasch.metro.dao.interfaces.StationDAO;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Graph;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.service.interfaces.PathFinderService;
import ru.javasch.metro.service.interfaces.StationService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j
public class PathFinderServiceImpl implements PathFinderService {

    private static final int NO_TRANSITION = 100000;
    private static final int TRANSITION = 50;
    private static final int MAX_TRANSITION = 10;
    private static final int NUMBER_OF_STATION = 69;

    @Autowired
    StationService stationService;

    @Autowired
    GraphDAO graphDAO;

    @Autowired
    StationDAO stationDAO;


    @Override
    @Transactional
    public List<Station> pathFinder(String stationBegin, String stationEnd) {
        Station stationBeg = stationDAO.findByName(stationBegin);
        Station stationEn = stationDAO.findByName(stationEnd);
        List<Graph> st1 = graphDAO.getAllFromNodes(stationBeg);
        List<Graph> st2 = graphDAO.getAllFromNodes(stationEn);
        int st1_branch = stationDAO.getAllStationOnBranch(stationBeg).size();
        int st2_branch = stationDAO.getAllStationOnBranch(stationEn).size();

        int closed1 = 0;
        int closed2 = 0;

        for (Graph r : st1)
            if (r.getWeight() == NO_TRANSITION && r.getOldWeight() != NO_TRANSITION)
                closed1++;

        for (Graph r : st2)
            if (r.getWeight() == NO_TRANSITION && r.getOldWeight() != NO_TRANSITION)
                closed2++;

        if (closed1 > st1_branch / 2)
            throw new RuntimeBusinessLogicException(ErrorCode.BEGIN_STATION_CLOSED);
        if (closed2 > st2_branch / 2)
            throw new RuntimeBusinessLogicException(ErrorCode.END_STATION_CLOSED);

        int indexBeg = stationService.findByName(stationBegin).getId() - 1;
        int indexEnd = stationService.findByName(stationEnd).getId() - 1;
        int[][] graphArray = new int[NUMBER_OF_STATION][NUMBER_OF_STATION];
        List<Integer> path = new ArrayList<>();
        List<Graph> graph = graphDAO.getAll();
        path.add(indexBeg);
        int index = 0;
        for (int i = 0; i < NUMBER_OF_STATION; i++) {
            for (int j = 0; j < NUMBER_OF_STATION; j++) {
                graphArray[i][j] = graph.get(index).getWeight();
                index++;
            }
        }
        int transCount = 0;
        int interIndex = indexBeg;
        List<Pair<Integer, Integer>> notIncluded = new ArrayList<>();
        if (graphArray[indexEnd][indexBeg] != NO_TRANSITION) {
            path.add(indexEnd);
        } else {
            /**               */

            while (true) {
                List<Integer> availTrans = new ArrayList<>();
                for (int i = 0; i < NUMBER_OF_STATION; i++) {
                    if (graphArray[interIndex][i] != NO_TRANSITION)
                        availTrans.add(i);
                }

                List<Pair<Integer, Integer>> interSt = new ArrayList<>();
                for (int j = 0; j < availTrans.size() - 1; j++) {
                    for (int k = 0; k < NUMBER_OF_STATION; k++) {
                        int l = availTrans.get(j);
                        if (graphArray[k][l] == TRANSITION)
                            interSt.add(new Pair<>(k, l));
                    }
                }
                interSt.removeAll(notIncluded);
                int bestTransition = NO_TRANSITION;
                int endDest = 0;
                for (Pair<Integer, Integer> pair : interSt) {
                    if (Math.abs(indexEnd - pair.getKey()) < Math.abs(indexEnd - bestTransition)) {
                        notIncluded.add(pair);
                        Integer a = pair.getValue();
                        Integer b = pair.getKey();
                        notIncluded.add(new Pair<>(a, b));
                        bestTransition = pair.getKey();
                        endDest = pair.getValue();
                    }
                }
                path.add(endDest);
                path.add(bestTransition);
                if (graphArray[bestTransition][indexEnd] != NO_TRANSITION)
                    break;
                interIndex = bestTransition;
                transCount++;
                if (transCount > MAX_TRANSITION)
                    throw new RuntimeBusinessLogicException(ErrorCode.ATS_ARE_CLOSED);
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
