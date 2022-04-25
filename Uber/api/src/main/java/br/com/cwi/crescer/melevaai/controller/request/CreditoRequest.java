package br.com.cwi.crescer.melevaai.controller.request;

import lombok.Data;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class CreditoRequest {

    @Min(1)
    private BigDecimal valor;
}
