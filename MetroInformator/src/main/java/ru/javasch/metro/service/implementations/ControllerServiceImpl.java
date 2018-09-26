package ru.javasch.metro.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.*;
import ru.javasch.metro.utils.EndPointStations;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ControllerServiceImpl implements ControllerService {
    private static int STATION_PAGES = 5;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private StationService stationService;

    @Autowired
    private PathFinderService pathFinderService;

    @Autowired
    private TrainService trainService;

    @Override
    public List<List<Ticket>> chainsOfTickets(String beginStation, String endStation, String date) throws ParseException {
        List<Station> stations = pathFinderService.pathFinder(beginStation, endStation);
        List<List<Station>> segments = stationService.formSegments(stations);
        stationService.checkSegments(segments);
        List<List<Station>> pathSegments = stationService.findPathSegments(segments);
        List<Schedule> schedules = ticketService.formFirstTicket(pathSegments, date);
        if (schedules.size() == 0)
            throw new RuntimeBusinessLogicException(ErrorCode.NO_TRAIN_ON_DATE);
        List<List<Ticket>> tickets = ticketService.formTicketChains(pathSegments, schedules);
        if (tickets.size() == 0)
            throw new RuntimeBusinessLogicException(ErrorCode.NO_TRAIN_ON_DATE);
        return tickets;
    }

    @Override
    public Map<String, Object> trainPagination() {
        Map<String, Object> pag = new HashMap<>();
        List<Train> trains = trainService.getAllTrains();
        Integer trainPages = trains.size() / 20 + 1;
        pag.put("trains", trains);
        pag.put("trainPages", trainPages);
        return pag;
    }

    @Override
    @Transactional
    public Map<String, Object> stationPagination(int stationPageNum) {
        Map<String, Object> pag = new HashMap<>();
        List<Station> stations = null;
        Integer stationPages = STATION_PAGES;
        switch (stationPageNum) {
            case 1:
                stations = stationService.getStationsBetweenIDs(EndPointStations.DEVYATKINO, EndPointStations.PROSPEKT_VETERANOV);
                break;
            case 2:
                stations = stationService.getStationsBetweenIDs(EndPointStations.PARNAS, EndPointStations.KUPCHINO);
                break;
            case 3:
                stations = stationService.getStationsBetweenIDs(EndPointStations.BEGOVAYA, EndPointStations.RYBATSKOYE);
                break;
            case 4:
                stations = stationService.getStationsBetweenIDs(EndPointStations.SPASSKAYA, EndPointStations.ULITSA_DYBENKO);
                break;
            case 5:
                stations = stationService.getStationsBetweenIDs(EndPointStations.KOMENDANTSKY_PROSPEKT, EndPointStations.MEZHDUNARODNAYA);
                break;
            default:
                break;
        }
        pag.put("stations", stations);
        pag.put("stationPages", stationPages);
        return pag;
    }

    @Override
    public String stationSwitchHelper(String color) {
        if (color.equals("RED"))
            return "redirect:/dashstation";
        else if (color.equals("BLUE"))
            return "redirect:/dashstation/2";
        else if (color.equals("GREEN"))
            return "redirect:/dashstation/3";
        else if (color.equals("ORANGE"))
            return "redirect:/dashstation/4";
        else
            return "redirect:/dashstation/5";
    }
}
