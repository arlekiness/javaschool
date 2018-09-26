package ru.javasch.metro.dao.implementations;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.dao.interfaces.TrainDAO;
import ru.javasch.metro.model.Train;

import java.util.List;

@Repository
public class TrainDAOImpl<E extends Train> extends GenericDAOImpl<E> implements TrainDAO<E> {

    @Override
    public Train findByName(String trainName) {
        try {
            return (Train) sessionFactory.getCurrentSession()
                    .createQuery("from Train where trainName = :trainName")
                    .setParameter("trainName", trainName)
                    .uniqueResult();
        } catch (NullPointerException ex) {
            return null;
        }
    }

    @Override
    public List<Train> getTrainByPage(int pageNum) {
        return (List<Train>) sessionFactory.getCurrentSession()
                .createQuery("from Train")
                .setFirstResult((pageNum - 1) * 20)
                .setMaxResults(20)
                .getResultList();
    }

}
