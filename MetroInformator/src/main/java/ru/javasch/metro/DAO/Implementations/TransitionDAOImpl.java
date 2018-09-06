package ru.javasch.metro.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.DAO.Interfaces.TrainDAO;
import ru.javasch.metro.DAO.Interfaces.TransitionDAO;
import ru.javasch.metro.model.*;

import java.util.List;

@Repository
public class TransitionDAOImpl<E extends Transition> extends GenericDAOImpl<E> implements TransitionDAO<E> {

    @Autowired
    private SessionFactory sessionFactory;


    public List<Transition> getTransitionsByStation (Station station) {
        return (List<Transition>)sessionFactory.getCurrentSession()
                .createQuery("from Transition where station_1_id = :station " +
                        "or station_2_id = :station")
                .setParameter("station", station)
                .getResultList();
    }

    public List<Transition> getTransitionsByBranch (Branch branch) {
        Long id = branch.getId();
        return (List<Transition>)sessionFactory.getCurrentSession()
                .createQuery("from Transition where station_1_id.branch_id = :id " +
                        "or station_2_id.branch_id = :id")
                .setParameter("id", id)
                .getResultList();
    }

}
