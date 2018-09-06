package ru.javasch.metro.DAO.Interfaces;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.model.Branch;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Transition;

import java.util.List;

@Repository
public interface TransitionDAO<E extends Transition> extends GenericDAO<E> {
    public List<Transition> getTransitionsByStation (Station station);
    public List<Transition> getTransitionsByBranch (Branch branch);
}
