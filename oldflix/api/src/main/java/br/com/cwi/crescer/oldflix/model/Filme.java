package br.com.cwi.crescer.oldflix.model;

import br.com.cwi.crescer.oldflix.Categoria;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private boolean disponivel;
    private Categoria categoria;
    private String responsavel;
    private LocalDate dataDeRetirada;
}
