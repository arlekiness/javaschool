package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.Status;

public interface StatusDAO {
    public Status getWorkStatus();
    public Status getCloseStatus();
    public Status getDestroyedStatus();
}
