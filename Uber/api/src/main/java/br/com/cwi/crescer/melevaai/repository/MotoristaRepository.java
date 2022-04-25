package br.com.cwi.crescer.melevaai.repository;

import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

    Motorista findFirstBySituacaoUsuarioOrderByAvaliacaoDesc(SituacaoUsuario situacaoUsuario);

    List<Motorista> findBySituacaoUsuario(SituacaoUsuario situacaoUsuario);
}
