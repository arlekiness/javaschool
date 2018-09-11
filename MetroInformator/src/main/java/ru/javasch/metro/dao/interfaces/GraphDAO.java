package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.Graph;
import ru.javasch.metro.model.Station;

import java.util.List;

public interface GraphDAO {
    public List<Graph> getAllNodes (Station station);
    public List<Graph> getAll ();
}
