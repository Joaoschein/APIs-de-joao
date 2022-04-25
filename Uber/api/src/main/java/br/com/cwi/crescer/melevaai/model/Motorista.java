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
public class Motorista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String CPF;

    @OneToOne(mappedBy = "motorista", cascade = CascadeType.ALL)
    private Veiculo veiculo;

    @OneToOne(mappedBy = "motorista", cascade = CascadeType.ALL)
    private CarteiraDeHabilitacao carteiraDeHabilitacao;

    @Enumerated(EnumType.STRING)
    private SituacaoUsuario situacaoUsuario;

    @OneToMany(mappedBy = "motorista")
    private List<Corrida> corridas;

    private String nome;
    private LocalDate dataNascimento;
    private BigDecimal avaliacao;
    private BigDecimal creditos;



}
