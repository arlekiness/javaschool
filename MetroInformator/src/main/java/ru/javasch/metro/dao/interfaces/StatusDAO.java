package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.Status;

public interface StatusDAO {
    Status getWorkStatus();

    Status getCloseStatus();

    Status getDestroyedStatus();
}
