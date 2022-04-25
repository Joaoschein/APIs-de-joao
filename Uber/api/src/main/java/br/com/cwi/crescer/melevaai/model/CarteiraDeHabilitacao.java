package br.com.cwi.crescer.melevaai.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
public class CarteiraDeHabilitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private LocalDate dataVencimento;
}
