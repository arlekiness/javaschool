package ru.javasch.metro.dao.implementations;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.dao.interfaces.StationDAO;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Branch;
import ru.javasch.metro.model.Station;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StationDAOImpl<E extends Station> extends GenericDAOImpl<E> implements StationDAO<E> {

    @Override
    public Station findByName(String name) {
        try {
            return (Station) sessionFactory.getCurrentSession()
                    .createQuery("from Station where name = :name")
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (Exception e) {
            throw new RuntimeBusinessLogicException("Such station doesn't exists");
        }
    }

    @Override
    public List<Station> getAllStationOnBranch(Station station) {
        Branch branch = station.getBranch();
        return (List<Station>) sessionFactory.getCurrentSession()
                .createQuery("from Station where branch = :branch")
                .setParameter("branch", branch)
                .getResultList();
    }

    @Override
    public Station findStationById(Integer id) {
        return (Station) sessionFactory.getCurrentSession()
                .createQuery("from Station where id = :id")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public List<Station> findAllStationBetweenBeginAndEndPoint(Station begin, Station end) {
        int beginId = begin.getNumberOnBranch();
        int endId = end.getNumberOnBranch();
        Branch branch = begin.getBranch();
        List<Station> list = new ArrayList<>();
        if (endId > beginId) {
            list = (List<Station>) sessionFactory.getCurrentSession()
                    .createQuery("from Station where branch=:branch and " + "numberOnBranch between (:beginId + 1) " + "and :endId " + "order by numberOnBranch asc")
                    .setParameter("beginId", beginId)
                    .setParameter("endId", endId)
                    .setParameter("branch", branch)
                    .getResultList();
        } else {
            list = (List<Station>) sessionFactory.getCurrentSession()
                    .createQuery("from Station where branch=:branch and " + "numberOnBranch between :endId " + "and (:beginId - 1) " + "order by numberOnBranch desc")
                    .setParameter("beginId", beginId)
                    .setParameter("endId", endId)
                    .setParameter("branch", branch)
                    .getResultList();
        }
        return list;

    }

    @Override
    public List<Station> getStationsBetweenIDs(Integer stationBeginId, Integer stationEndId) {
        return (List<Station>) sessionFactory.getCurrentSession()
                .createQuery("from Station where id >= :stationBeginId " + "and id <= :stationEndId")
                .setParameter("stationBeginId", stationBeginId)
                .setParameter("stationEndId", stationEndId)
                .getResultList();
    }


}
