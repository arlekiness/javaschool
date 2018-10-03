package ru.javasch.metro.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.dao.interfaces.LastDateDAO;
import ru.javasch.metro.model.LastDateSchedule;

import java.util.Date;

@Service
public class LastDateService {
    @Autowired
    private LastDateDAO lastDateDAO;

    public LastDateSchedule getLastDate() {return lastDateDAO.getLastDate();}
    public LastDateSchedule getFromDate() {return lastDateDAO.getFromDate();}
}
