package ru.javasch.metro.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

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
        log.error(ex.getError(), ex);
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
                return new ModelAndView("tickets", "beginStationClosed", true);
            case ATS_ARE_CLOSED:
                return new ModelAndView("tickets", "ATSClosed", true);
            case EMPTY_FIELDS:
                model.setViewName("registration");
                model.addObject("emptyfields", ex);
                return model;
            case END_STATION_CLOSED:
                return new ModelAndView("tickets", "endStationClosed", true);
            case NO_TRAIN_ON_DATE:
                return new ModelAndView("tickets", "noTrainsOnDate", true);
            case NO_MORE_TICKETS:
                log.info("EXCEPTION: " + ex.getError());
                HttpSession session = request.getSession();
                session.removeAttribute("TicketList");
                return new ModelAndView("redirect:/ticketsFail");
            case STATION_CLOSED:
                modelMap.put("closedStationStatus", "true");
                return new ModelAndView("schedule", "model", modelMap);
            case TO_LATE_FOR_TRAIN:
                log.info("EXCEPTION: " + ex.getError());
                return new ModelAndView("redirect:/dashtrain", "train", true);
            case TRAIN_EXIST:
                log.info("EXCEPTION: " + ex.getError());
                return new ModelAndView("redirect:/dashtrain", "train", true);
            case INCORRECT_EMAIL:
                model.setViewName("registration");
                model.addObject("uncpass", ex);
                return model;
            case INCORRECT_PASSWORD:
                model.setViewName("registration");
                model.addObject("uncpass", ex);
                return model;
            case USER_EXIST:
                model.setViewName("registration");
                model.addObject("exist", ex);
                return model;
            case EMPTY_FIELDS_TRAIN_FORM:
                log.info("EXCEPTION: " + ex.getError());
                return new ModelAndView("redirect:/dashtrain", "train", true);
            default:
                return new ModelAndView("error");
        }
    }

    @ExceptionHandler(IOException.class)
    public ModelAndView handleIOException(IOException ex) {
        log.error(ex, ex);
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("closedStationStatus", "true");
        return new ModelAndView("schedule", "model", modelMap);
    }

    @ExceptionHandler(MessagingException.class)
    public ModelAndView handleMessagingException(MessagingException ex) {
        log.error(ex, ex);
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("closedStationStatus", "true");
        return new ModelAndView("schedule", "model", modelMap);
    }


}
