package ru.javasch.metro.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.GraphDAO;
import ru.javasch.metro.dao.interfaces.StationDAO;
import ru.javasch.metro.model.Graph;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.service.interfaces.GraphService;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * ************************************************
 * SERVICE FOR WORK WITH GRAPH
 * ************************************************
 */
@Service
public class GraphServiceImpl implements GraphService {

    private static final int NO_TRANSITION = 100000;
    private static final int TRANSITION = 50;

    @Autowired
    private GraphDAO graphDAO;

    @Autowired
    private StationDAO stationDAO;

    /**CHANGING WEIGHTS IN GRAPH THROUGH
     * OPENING AND CLOSING STATIONS
     *
     * @param stationName
     * @return
     */
    @Override
    @Transactional
    public List<Station> changeWeight(String stationName) {
        Station station = stationDAO.findByName(stationName);
        List<Graph> graph = graphDAO.getAllNodes(station);
        List<Graph> graphFrom = graphDAO.getAllFromNodes(station);
        List<Station> transitStations = new ArrayList<>();
        for (Graph gr : graphFrom) {
            if ((gr.getWeight() == TRANSITION && gr.getOldWeight() == NO_TRANSITION) || (gr.getOldWeight() == TRANSITION && gr.getWeight() == NO_TRANSITION))
                transitStations.add(gr.getStationTo());
        }
        for (Station st : transitStations) {
            List<Graph> graphOnTransitStations = graphDAO.getAllNodes(st);
            graphOnTransitStations.removeAll(graph);
            graph.addAll(graphOnTransitStations);
        }

        for (Graph gr : graph) {
            int weight = gr.getWeight();
            int oldWeight = gr.getOldWeight();
            gr.setOldWeight(weight);
            gr.setWeight(oldWeight);
        }

        return transitStations;
    }
}
