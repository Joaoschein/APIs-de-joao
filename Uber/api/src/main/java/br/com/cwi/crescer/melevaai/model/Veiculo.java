package br.com.cwi.crescer.melevaai.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Builder
@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "motorista_id")
    @JsonIgnore
    private Motorista motorista;

    @Column(unique=true)
    private String placa;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private String modelo;
    private String cor;
    private String foto;



}
