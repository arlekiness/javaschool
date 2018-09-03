package ru.javasch.metro.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.TrainDAO;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;

@Repository
public class TrainDAOImpl<E extends Train> extends GenericDAOImpl<E> implements TrainDAO<E> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Train findByName(String trainName) {
        return (Train) sessionFactory.getCurrentSession()
                .createQuery("from Train where trainName = :trainName")
                .setParameter("trainName", trainName)
                .uniqueResult();
    }
}
