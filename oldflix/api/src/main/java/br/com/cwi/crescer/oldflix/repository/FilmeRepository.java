package br.com.cwi.crescer.oldflix.repository;

import br.com.cwi.crescer.oldflix.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}
