package ru.javasch.metro.service.Interfaces;

import org.springframework.stereotype.Service;

@Service
public interface GraphService {
    public void changeWeight (String stationName);
}
