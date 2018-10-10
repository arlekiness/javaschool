package ru.javasch.metro.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.javasch.metro.utils.URLs;
import ru.javasch.metro.utils.VIEWs;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Log4j
public class MetroExceptionHandler {

    private ErrorCodeEnum Error;

    @ExceptionHandler(RuntimeBusinessLogicException.class)
    public ModelAndView handleRuntimeBusinessLogicException(RuntimeBusinessLogicException ex, HttpServletRequest request) {
//        log.error(ex.getError(), ex);
        Map<String, Object> modelMap = new HashMap<>();
        ModelAndView model = new ModelAndView();
        String error = ex.getError();
        for (ErrorCodeEnum Err : ErrorCodeEnum.values()) {
            if (ex.getError().equals(Err.getReason())) {
                Error = Err;
                break;
            }
        }

        switch (Error) {
            case BEGIN_STATION_CLOSED:
                return new ModelAndView(VIEWs.TICKETS, "beginStationClosed", true);
            case ATS_ARE_CLOSED:
                return new ModelAndView(VIEWs.TICKETS, "ATSClosed", true);
            case EMPTY_FIELDS:
                model.setViewName(VIEWs.REGISTRATION);
                model.addObject("emptyfields", ex);
                return model;
            case END_STATION_CLOSED:
                return new ModelAndView(VIEWs.TICKETS, "endStationClosed", true);
            case NO_TRAIN_ON_DATE:
                return new ModelAndView(VIEWs.TICKETS, "noTrainsOnDate", true);
            case INCORRECT_DATE_TICKETS:
                return new ModelAndView(VIEWs.TICKETS, "pastDate", true);
            case NO_MORE_TICKETS:
                HttpSession session = request.getSession();
                session.removeAttribute("TicketList");
                return new ModelAndView(URLs.REDIRECT_TICKET_FAIL);
            case STATION_CLOSED:
                modelMap.put("closedStationStatus", "true");
                return new ModelAndView(VIEWs.SCHEDULE, "model", modelMap);
            case INCORRECT_DATE_SCHEDULE:
                modelMap.put("oldDate", "true");
                return new ModelAndView(VIEWs.SCHEDULE, "model", modelMap);
            case TO_LATE_FOR_TRAIN:
                return new ModelAndView(VIEWs.CREATE_TRAIN, "lateTrain", true);
            case TRAIN_EXIST:
                return new ModelAndView(VIEWs.CREATE_TRAIN, "trainExist", true);
            case THAT_TIME_ALREADY_USED_BY_ANOTHER_TRAIN:
                return new ModelAndView(VIEWs.CREATE_TRAIN, "usedTime", true);
            case NOT_AUTHORIZED_ADDING:
                return new ModelAndView(VIEWs.CREATE_TRAIN, "notAuthorizedAdding", true);
            case INCORRECT_EMAIL:
                model.setViewName(VIEWs.REGISTRATION);
                model.addObject("uncem", ex);
                return model;
            case INCORRECT_CRED:
                model.setViewName(VIEWs.REGISTRATION);
                model.addObject("uncpass", ex);
                return model;
            case USER_EXIST:
                model.setViewName(VIEWs.REGISTRATION);
                model.addObject("exist", ex);
                return model;
            case EMPTY_FIELDS_TRAIN_FORM:
                return new ModelAndView(VIEWs.CREATE_TRAIN, "emptyFields", true);
            case TRAIN_IN_PAST:
                return new ModelAndView(VIEWs.CREATE_TRAIN, "trainInPast", true);
            case DONT_KNOW_STATION:
                return new ModelAndView(VIEWs.CREATE_TRAIN, "dontknowstation", true);
            default:
                return new ModelAndView(VIEWs.ERROR);
        }
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(IOException ex) {
        log.error(ex, ex);
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("closedStationStatus", "true");
        return new ModelAndView(VIEWs.SCHEDULE, "model", modelMap);
    }

    @ExceptionHandler(MessagingException.class)
    public ModelAndView handleMessagingException(MessagingException ex) {
        log.error(ex, ex);
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("closedStationStatus", "true");
        return new ModelAndView(VIEWs.SCHEDULE, "model", modelMap);
    }

    @ExceptionHandler(Exception.class)
    public String handleMessagingException(Exception ex) {
        log.error(ex, ex);

        return VIEWs.ERROR;
    }

}
