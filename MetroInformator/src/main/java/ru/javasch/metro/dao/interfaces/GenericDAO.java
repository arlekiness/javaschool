package ru.javasch.metro.dao.interfaces;

import java.util.List;

public interface GenericDAO<E> {

    void add(E entity);

    void update(E entity);

    void delete(E entity);

    List<E> getAll();

    E getById(Long id);
}
