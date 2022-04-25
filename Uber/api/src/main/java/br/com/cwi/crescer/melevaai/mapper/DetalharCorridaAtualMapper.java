package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.controller.response.DetalharCorridaResponse;
import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.model.SituacaoCorrida;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetalharCorridaAtualMapper {

    @Autowired
    private CorridaRepository corridaRepository;

    public DetalharCorridaResponse toResponse(Motorista entity){
        Corrida corridaAtual = corridaRepository.findFirstByMotoristaAndSituacaoCorridaNot(entity, SituacaoCorrida.FINALIZADA);
        if (corridaAtual != null) {
            return DetalharCorridaResponse.builder()
                    .id(corridaAtual.getId())
                    .nomeMotorista(corridaAtual.getMotorista().getNome())
                    .nomePassageiro(corridaAtual.getPassageiro().getNome())
                    .distancia(corridaAtual.getDistancia())
                    .situacao(corridaAtual.getSituacaoCorrida())
                    .build();
        }else {
            return null;
        }
    }

    public DetalharCorridaResponse toResponse(Passageiro entity){
        Corrida corridaAtual = corridaRepository.findFirstByPassageiroAndSituacaoCorridaNot(entity, SituacaoCorrida.FINALIZADA);
        if (corridaAtual != null) {
            return DetalharCorridaResponse.builder()
                    .id(corridaAtual.getId())
                    .nomeMotorista(corridaAtual.getMotorista().getNome())
                    .nomePassageiro(corridaAtual.getPassageiro().getNome())
                    .distancia(corridaAtual.getDistancia())
                    .situacao(corridaAtual.getSituacaoCorrida())
                    .build();
        }else {
            return null;
        }
    }
}
