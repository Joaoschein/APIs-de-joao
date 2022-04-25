package br.com.cwi.crescer.melevaai.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
@Entity
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String CPF;

    @Enumerated(EnumType.STRING)
    private SituacaoUsuario situacaoUsuario;

    @OneToMany(mappedBy = "passageiro")
    private List<Corrida> corridas;

    private String nome;
    private LocalDate dataNascimento;
    private BigDecimal avaliacao;
    private BigDecimal creditos;


}
