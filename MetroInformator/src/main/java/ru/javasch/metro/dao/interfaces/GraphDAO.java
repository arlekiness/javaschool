package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.Graph;
import ru.javasch.metro.model.Station;

import java.util.List;

public interface GraphDAO {
    List<Graph> getAllNodes(Station station);

    List<Graph> getAllFromNodes(Station station);

    List<Graph> getAllToNodes(Station station);

    List<Graph> getAll();
}
