package ru.javasch.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.DAO.Interfaces.StationDAO;
import ru.javasch.metro.DTO.TicketDTO;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.model.Train;
import ru.javasch.metro.service.Interfaces.StationService;
import ru.javasch.metro.service.Interfaces.TicketService;
import ru.javasch.metro.service.Interfaces.TrainService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BuyingTicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private StationService stationService;

    @Autowired
    private TrainService trainService;

    @RequestMapping(value="/buyTicket/{station}/{dateDeparture}/{trainName}/{endPointStation}/{login}")
    public ModelAndView stationSchedule(@PathVariable(value="station") String stationName,
                                        @PathVariable(value="dateDeparture") String dateDeparture,
                                        @PathVariable(value="trainName") String trainName,
                                        @PathVariable(value="endPointStation") String endPointStation,
                                        @PathVariable(value="login") String userId,
                                        HttpServletRequest request, HttpServletResponse response) {
        TicketDTO ticketDTO = ticketService.formBeginTicketDTO(stationName, dateDeparture, trainName, endPointStation, userId);
        List<Station> st = stationService.getAllStationsBeetweenTwoPoints(stationName, endPointStation);
        List<String> stations = new ArrayList<>();
        for (Station station : st)
            stations.add(station.getName());
        HttpSession session = request.getSession();
        session.setAttribute("ticketDTO", ticketDTO);
        ModelAndView model = new ModelAndView();
        model.addObject("stationList", stations);
        model.setViewName("buyticket");
        return model;
    }

    @RequestMapping(value="/buyTicketFinal/{station}")
    public String buyingTicketFinal(@PathVariable(value="station") String endStation, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TicketDTO ticketDTO = (TicketDTO) session.getAttribute("ticketDTO");
        ticketDTO.setStationEnd(endStation);
        try {
            Train train = trainService.findByName(ticketDTO.getTrainName());
            if (train.getCapacity() <= ticketService.occupiedSeats(ticketDTO))
                System.out.println("You can't buy any more tickets");
            else {
                ticketService.addTicketInSystem(ticketDTO);
                session.removeAttribute("ticketDTO");
            }
        } catch (Exception ex) {}
        return "station";
    }

}
