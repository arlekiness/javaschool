package ru.javasch.metro.DAO.Implementations;

import org.hibernate.NonUniqueResultException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DAO.Interfaces.TrainDAO;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.exception.BusinessLogicException;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;

import java.util.Date;
import java.util.List;

@Repository
public class TrainDAOImpl<E extends Train> extends GenericDAOImpl<E> implements TrainDAO<E> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Train findByName(String trainName) {
        try {
            return (Train) sessionFactory.getCurrentSession()
                    .createQuery("from Train where trainName = :trainName")
                    .setParameter("trainName", trainName)
                    .uniqueResult();
        } catch (NonUniqueResultException ex) {
            throw new RuntimeBusinessLogicException("Train already exist");
        } catch (NullPointerException ex) {
            throw new RuntimeBusinessLogicException("Such train doesn't exist");
        }
    }

}
