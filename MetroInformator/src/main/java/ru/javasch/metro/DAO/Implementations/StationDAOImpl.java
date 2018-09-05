package ru.javasch.metro.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.model.Branch;
import ru.javasch.metro.model.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class StationDAOImpl<E extends Station> extends GenericDAOImpl<E> implements StationDAO<E> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Station findByName(String name) {
        return (Station) sessionFactory.getCurrentSession()
                .createQuery("from Station where name = :name")
                .setParameter("name", name)
                .uniqueResult();
    }

    @Override
    public List<Station> getAllStationOnBranch(Station station) {
        Branch branch = station.getBranch();
        return (List<Station>) sessionFactory.getCurrentSession()
                .createQuery("from Station where branch = :branch")
                .setParameter("branch", branch)
                .getResultList();
    }

//    public Set<Station> getTransitionsOnName (String name) {
//        return (Set<Station>) sessionFactory.getCurrentSession()
//                .createQuery("from Station where name = :name")
//                .setParameter("name", name)
//                .uniqueResult();
//    }
    @Override
    public List<Station> findAllStationBetweenBeginAndEndPoint (Station begin, Station end) {
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
        } else
            list = (List<Station>) sessionFactory.getCurrentSession()
                    .createQuery("from Station where branch=:branch and " + "numberOnBranch between :endId " + "and (:beginId - 1) " + "order by numberOnBranch desc")
                    .setParameter("beginId", beginId)
                    .setParameter("endId", endId)
                    .setParameter("branch", branch)
                    .getResultList();
        return list;
    }




}
