package br.com.cwi.crescer.melevaai.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GerarTempoEstimadoService {
    public int gerar(){
        Random random = new Random();
        return random.nextInt(10 - 5 + 1) + 5;
    }
}
