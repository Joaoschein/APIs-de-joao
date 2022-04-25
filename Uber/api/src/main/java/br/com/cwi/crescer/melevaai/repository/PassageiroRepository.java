package br.com.cwi.crescer.melevaai.repository;

import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {

    List<Passageiro> findBySituacaoUsuario(SituacaoUsuario situacaoUsuario);
}
