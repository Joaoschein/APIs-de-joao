package br.com.cwi.crescer.melevaai.controller.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class AvaliacaoRequest {
    private static final int NOTA_MINIMA = 1;
    private static final int NOTA_MAXIMA = 5;

    @Min(NOTA_MINIMA)
    @Max(NOTA_MAXIMA)
    private int nota;
}
