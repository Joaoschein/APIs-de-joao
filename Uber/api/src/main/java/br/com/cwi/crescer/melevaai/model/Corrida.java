package br.com.cwi.crescer.melevaai.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
@Entity
public class Corrida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SituacaoCorrida situacaoCorrida;

    @ManyToOne(cascade = {CascadeType.PERSIST ,CascadeType.MERGE})
    @JoinColumn(name = "passageiro_id")
    private Passageiro passageiro;

    @ManyToOne(cascade = {CascadeType.PERSIST ,CascadeType.MERGE})
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    private int avaliacaoMotorista;

    private int avaliacaoPassageiro;

    private LocalDateTime horaInicio;

    private LocalDateTime horaFinal;

    private Double distancia;

}
