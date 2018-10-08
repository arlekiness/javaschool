package ru.javasch.metro.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.log4j.Log4j;
import ru.javasch.metro.model.Schedule;
import ru.javasch.metro.model.Station;
import ru.javasch.metro.utils.Utils;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Log4j
public class Loader {
    private static Loader loader;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Client client = new Client();

    public synchronized static Loader getInstance() {
        if (loader == null)
            loader = new Loader();
        return loader;
    }

    public List<Schedule> getSchedules() {
        String response = getResultResponse(Utils.URL_SCHEDULES);
        List<Schedule> schedules = null;
        try {
            schedules = objectMapper.readValue(response, new TypeReference<List<Schedule>>() {
            });
        } catch (IOException e) {
            log.error("ERROR, CAN'T LOAD SCHEDULES " + e.getMessage());
        }
        return schedules;
    }

    public List<Station> getStations() {
        String response = getResultResponse(Utils.URL_STATIONS);
        List<Station> stations = null;
        try {
            stations = objectMapper.readValue(response, new TypeReference<List<Station>>() {
            });
        } catch (IOException e) {
            log.error("ERROR, CAN'T LOAD STATIONS " + e.getMessage());
        }
        return stations;
    }

    private String getResultResponse(String URL) {
        WebResource webResource = client.resource(URL);
        return webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
    }
}
