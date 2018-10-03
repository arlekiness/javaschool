package ru.javasch.metro.dao.interfaces;

import ru.javasch.metro.model.LastDateSchedule;

import java.util.Date;

public interface LastDateDAO {
    LastDateSchedule getLastDate();
    LastDateSchedule getFromDate();
}
