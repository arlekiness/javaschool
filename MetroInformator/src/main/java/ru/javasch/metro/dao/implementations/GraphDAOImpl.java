package ru.javasch.metro.dao.implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.dao.interfaces.GraphDAO;
import ru.javasch.metro.model.Graph;
import ru.javasch.metro.model.Station;

import java.util.List;

@Repository
public class GraphDAOImpl implements GraphDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Graph> getAllFromNodes(Station station) {
        return (List<Graph>) sessionFactory.getCurrentSession()
                .createQuery("from Graph where stationFrom = :station")
                .setParameter("station", station)
                .getResultList();
    }

    @Override
    public List<Graph> getAllToNodes(Station station) {
        return (List<Graph>) sessionFactory.getCurrentSession()
                .createQuery("from Graph where stationTo = :station")
                .setParameter("station", station)
                .getResultList();
    }

    @Override
    public List<Graph> getAllNodes(Station station) {
        return (List<Graph>) sessionFactory.getCurrentSession()
                .createQuery("from Graph where stationFrom = :station or stationTo = :station")
                .setParameter("station", station)
                .getResultList();
    }

    @Override
    public List<Graph> getAll() {
        return (List<Graph>) sessionFactory.getCurrentSession()
                .createQuery("from Graph")
                .getResultList();
    }

}
