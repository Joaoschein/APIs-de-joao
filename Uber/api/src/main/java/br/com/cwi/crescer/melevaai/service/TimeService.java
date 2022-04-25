package br.com.cwi.crescer.melevaai.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class TimeService {

    public LocalDate now() {
        return LocalDate.now();
    }

    public LocalDateTime nowWithTime() {
        return LocalDateTime.now();
    }
}
