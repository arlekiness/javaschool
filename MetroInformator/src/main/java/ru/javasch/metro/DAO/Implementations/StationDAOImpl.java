package ru.javasch.metro.DAO.Implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.model.Station;

import java.util.List;

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



}
