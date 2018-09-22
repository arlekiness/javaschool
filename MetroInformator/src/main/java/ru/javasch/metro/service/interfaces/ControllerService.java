package ru.javasch.metro.service.interfaces;

import org.springframework.stereotype.Service;
import ru.javasch.metro.model.Ticket;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
public interface ControllerService {
    List<List<Ticket>> chainsOfTickets(String beginStation, String endStation, String date) throws ParseException;
    Map<String, Object> pagination ();
    Map<String, Object> trainpagination ();
    Map<String, Object> stationpagination ();
}
