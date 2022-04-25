package br.com.cwi.crescer.melevaai.repository;

import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.model.SituacaoCorrida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface CorridaRepository extends JpaRepository<Corrida, Long> {

    Corrida findFirstByMotoristaAndSituacaoCorridaNotLike(Motorista motorista, SituacaoCorrida situacaoCorrida);

    @Query("SELECT AVG(c.avaliacaoMotorista) FROM Corrida c WHERE c.motorista =?1 AND c.avaliacaoMotorista != 0")
    BigDecimal getMediaDeAvaliacoesMotorista(Motorista motorista);

    @Query("SELECT AVG(c.avaliacaoPassageiro) FROM Corrida c WHERE c.passageiro =?1 AND c.avaliacaoPassageiro != 0")
    BigDecimal getMediaDeAvaliacoesPassageiro(Passageiro passageiro);

    Corrida findFirstByMotoristaAndSituacaoCorridaNot(Motorista motorista, SituacaoCorrida situacaoCorrida);

    Corrida findFirstByPassageiroAndSituacaoCorridaNot(Passageiro passageiro, SituacaoCorrida situacaoCorrida);
}
