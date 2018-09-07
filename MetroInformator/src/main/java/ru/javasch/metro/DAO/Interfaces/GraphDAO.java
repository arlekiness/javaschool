package ru.javasch.metro.DAO.Interfaces;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.model.Graph;
import ru.javasch.metro.model.Station;

import java.util.List;

@Repository
public interface GraphDAO {
    public List<Graph> getAllNodes (Station station);
    public List<Graph> getAll ();
}
