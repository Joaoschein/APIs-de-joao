package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.repository.PassageiroRepository;
import br.com.cwi.crescer.melevaai.util.PassageiroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarPassageiroServiceTest {

    @InjectMocks
    private BuscarPassageiroService service;

    @Mock
    private PassageiroRepository passageiroRepository;

    @Test
    @DisplayName("Deve retornar passageiro com sucesso ao buscar passageiro existente")
    void deveRetornarPassageiroAoBuscarPassageiroExistente() {
        when(passageiroRepository.findById(1L))
                .thenReturn(Optional.ofNullable(PassageiroFactory.get()));

        Passageiro resultado = service.buscarPorId(1L);

        verify(passageiroRepository).findById(1L);

        assertEquals("99911199900", resultado.getCPF());
    }

    @Test
    @DisplayName("Deve lançar erro ao buscar passageiro inexistente")
    void deeLancarErroAoBuscarPassageiroInexistente() {
        when(passageiroRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            service.buscarPorId(1L);
        });

        verify(passageiroRepository).findById(1L);
    }
}