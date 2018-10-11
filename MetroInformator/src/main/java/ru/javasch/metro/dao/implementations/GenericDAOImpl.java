package ru.javasch.metro.dao.implementations;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Repository;
import ru.javasch.metro.dao.interfaces.GenericDAO;

import java.util.List;

@Repository
public abstract class GenericDAOImpl<E> implements GenericDAO<E> {
    @Autowired
    SessionFactory sessionFactory;


    private Class<E> entityClass;

    public GenericDAOImpl() {
        this.entityClass = (Class<E>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDAO.class);
    }

    @Override
    public void add(E entity) {
        sessionFactory.getCurrentSession()
                .save(entity);
    }

    @Override
    public void update(E entity) {
        sessionFactory.getCurrentSession()
                .update(entity);
    }

    @Override
    public void delete(E entity) {
        sessionFactory.getCurrentSession()
                .delete(entity);
    }

    @Override
    public List<E> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from " + entityClass.getName())
                .getResultList();
    }

    @Override
    public E getById(Long id) {
        return sessionFactory.getCurrentSession()
                .get(entityClass, id);
    }
}
