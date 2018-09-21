package ru.javasch.metro.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.exception.ErrorCode;
import ru.javasch.metro.exception.RuntimeBusinessLogicException;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Ticket;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.interfaces.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ControllerServiceImpl implements ControllerService {
    @Autowired
    private TicketService ticketService;

    @Autowired
    private StationService stationService;

    @Autowired
    private UserService userService;

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
        String path = ticketService.formMessageAboutPath(segments);
        List<Schedule> schedules = ticketService.formFirstTicket(pathSegments, date);
        if (schedules.size() == 0)
            throw new RuntimeBusinessLogicException(ErrorCode.NO_TRAIN_ON_DATE);
        List<List<Ticket>> tickets = ticketService.formTicketChains(pathSegments, schedules);
        if (tickets.size() == 0)
            throw new RuntimeBusinessLogicException(ErrorCode.NO_TRAIN_ON_DATE);
        return tickets;
    }

    @Override
    public Map<String, Object> pagination () {
        Map<String, Object> pag = new HashMap<>();
        List<Train> trains = trainService.getAllTrains();
        List<Station> stations = stationService.getAllStations();
        Integer trainPages = trains.size() / 20 + 1;
        Integer stationPages = 5;
        pag.put("trains", trains);
        pag.put("stations", stations);
        pag.put("trainPages", trainPages);
        pag.put("stationPages", stationPages);
        return pag;
    }
}
