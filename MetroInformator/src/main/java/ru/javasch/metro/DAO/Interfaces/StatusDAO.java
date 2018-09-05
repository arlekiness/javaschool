package ru.javasch.metro.DAO.Interfaces;

import org.springframework.stereotype.Repository;
import ru.javasch.metro.model.Status;

@Repository
public interface StatusDAO {
    public Status getWorkStatus();
    public Status getCloseStatus();
    public Status getDestroyedStatus();
}
