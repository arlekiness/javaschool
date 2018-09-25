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
        List<Graph> nodesWithBeginStation = graphDAO.getAllFromNodes(stationBeg);
        List<Graph> nodesWithEndStation = graphDAO.getAllFromNodes(stationEn);
        int stationCountOnBeginBranch = stationDAO.getAllStationOnBranch(stationBeg).size();
        int stationCountOnEndBranch = stationDAO.getAllStationOnBranch(stationEn).size();

        int endlessWeightOnBeginStation = 0;
        int endlessWeightOnEndStation = 0;

        for (Graph r : nodesWithBeginStation)
            if (r.getWeight() == NO_TRANSITION && r.getOldWeight() != NO_TRANSITION)
                endlessWeightOnBeginStation++;

        for (Graph r : nodesWithEndStation)
            if (r.getWeight() == NO_TRANSITION && r.getOldWeight() != NO_TRANSITION)
                endlessWeightOnEndStation++;

        if (endlessWeightOnBeginStation > stationCountOnBeginBranch / 2)
            throw new RuntimeBusinessLogicException(ErrorCode.BEGIN_STATION_CLOSED);
        if (endlessWeightOnEndStation > stationCountOnEndBranch / 2)
            throw new RuntimeBusinessLogicException(ErrorCode.END_STATION_CLOSED);

        int beginStationIndex = stationService.findByName(stationBegin).getId() - 1;
        int endStationIndex = stationService.findByName(stationEnd).getId() - 1;
        int[][] graphArray = new int[NUMBER_OF_STATION][NUMBER_OF_STATION];
        List<Integer> path = new ArrayList<>();
        List<Graph> graph = graphDAO.getAll();
        path.add(beginStationIndex);
        int idIndexInGraph = 0;
        for (int i = 0; i < NUMBER_OF_STATION; i++) {
            for (int j = 0; j < NUMBER_OF_STATION; j++) {
                graphArray[i][j] = graph.get(idIndexInGraph).getWeight();
                idIndexInGraph++;
            }
        }
        int transitionCount = 0;
        int intermediateIndex = beginStationIndex;
        List<Pair<Integer, Integer>> notIncludedTransitions = new ArrayList<>();
        if (graphArray[endStationIndex][beginStationIndex] != NO_TRANSITION) {
            path.add(endStationIndex);
        } else {
            /**               */

            while (true) {
                List<Integer> availibleTrans = new ArrayList<>();
                for (int i = 0; i < NUMBER_OF_STATION; i++) {
                    if (graphArray[intermediateIndex][i] != NO_TRANSITION)
                        availibleTrans.add(i);
                }

                List<Pair<Integer, Integer>> intermediateStations = new ArrayList<>();
                for (int j = 0; j < availibleTrans.size() - 1; j++) {
                    for (int k = 0; k < NUMBER_OF_STATION; k++) {
                        int l = availibleTrans.get(j);
                        if (graphArray[k][l] == TRANSITION)
                            intermediateStations.add(new Pair<>(k, l));
                    }
                }
                intermediateStations.removeAll(notIncludedTransitions);
                int bestTransition = NO_TRANSITION;
                int endDest = 0;
                for (Pair<Integer, Integer> pair : intermediateStations) {
                    if (Math.abs(endStationIndex - pair.getKey()) < Math.abs(endStationIndex - bestTransition)) {
                        notIncludedTransitions.add(pair);
                        Integer a = pair.getValue();
                        Integer b = pair.getKey();
                        notIncludedTransitions.add(new Pair<>(a, b));
                        bestTransition = pair.getKey();
                        endDest = pair.getValue();
                    }
                }
                if(bestTransition == NO_TRANSITION)
                    throw new RuntimeBusinessLogicException(ErrorCode.ATS_ARE_CLOSED);
                path.add(endDest);
                path.add(bestTransition);
                if (graphArray[bestTransition][endStationIndex] != NO_TRANSITION)
                    break;
                intermediateIndex = bestTransition;
                transitionCount++;
                if (transitionCount > MAX_TRANSITION)
                    throw new RuntimeBusinessLogicException(ErrorCode.ATS_ARE_CLOSED);
            }
            path.add(endStationIndex);
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
