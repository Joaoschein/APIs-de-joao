package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.util.MotoristaFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarMotoristaServiceTest {

    @InjectMocks
    private BuscarMotoristaService service;

    @Mock
    private MotoristaRepository motoristaRepository;

    @Test
    @DisplayName("Deve retornar motorista com sucesso ao buscar motorista existente")
    void deveRetornarMotoristaAoBuscarMotoristaExistente() {
        when(motoristaRepository.findById(1L))
                .thenReturn(Optional.ofNullable(MotoristaFactory.get()));

        Motorista resultado = service.buscarPorId(1L);

        verify(motoristaRepository).findById(1L);

        assertEquals("00011100011", resultado.getCPF());
    }

    @Test
    @DisplayName("Deve lancar erro ao buscar motorista inexistente")
    void deveLancarErroAoBuscarMotoristaInexistente() {
        when(motoristaRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            service.buscarPorId(1L);
        });

        verify(motoristaRepository).findById(1L);
    }

    @Test
    @DisplayName("Deve retornar motorista com sucesso ao buscar motorista livre com maior avaliacao")
    void deveRetornarMotoristaAoBuscarMotoristaLivreComMaiorNotaExistente() {
        when(motoristaRepository.findFirstBySituacaoUsuarioOrderByAvaliacaoDesc(SituacaoUsuario.LIVRE))
                .thenReturn(MotoristaFactory.get());

        Motorista resultado = service.buscarLivreComMaiorAvaliacao();

        verify(motoristaRepository).findFirstBySituacaoUsuarioOrderByAvaliacaoDesc(SituacaoUsuario.LIVRE);

        assertEquals("00011100011", resultado.getCPF());
    }

    @Test
    @DisplayName("Deve lançar erro ao buscar motorista inexistente livre com maior avaliacao")
    void deveLancarErroAoBuscarMotoristaInexistenteLivreComMaiorAvaliacao() {
        when(motoristaRepository.findFirstBySituacaoUsuarioOrderByAvaliacaoDesc(SituacaoUsuario.LIVRE))
                .thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> {
            service.buscarLivreComMaiorAvaliacao();
        });

        verify(motoristaRepository).findFirstBySituacaoUsuarioOrderByAvaliacaoDesc(SituacaoUsuario.LIVRE);
    }
}