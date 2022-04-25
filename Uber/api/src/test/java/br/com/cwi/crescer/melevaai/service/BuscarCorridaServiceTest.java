package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.Corrida;
import br.com.cwi.crescer.melevaai.repository.CorridaRepository;
import br.com.cwi.crescer.melevaai.util.CorridaFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarCorridaServiceTest {

    @InjectMocks
    private BuscarCorridaService service;

    @Mock
    private CorridaRepository corridaRepository;

    @Test
    @DisplayName("Deve retornar corrida com sucesso ao buscar corrida existente")
    void deveRetornarCorridaAoBuscarCorridaExistente() {
        when(corridaRepository.findById(1L))
                .thenReturn(Optional.ofNullable(CorridaFactory.get()));

        Corrida resultado = service.buscarPorId(1L);

        verify(corridaRepository).findById(1L);

        assertEquals(1L, resultado.getId());
    }

    @Test
    @DisplayName("Deve lançar erro ao buscar corrida inexistente")
    void deveLancarErroAoBuscarCorridaInexistente() {
        when(corridaRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            service.buscarPorId(1L);
        });

        verify(corridaRepository).findById(1L);
    }
}