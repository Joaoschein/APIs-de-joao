package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.controller.request.CreditoRequest;
import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.repository.PassageiroRepository;
import br.com.cwi.crescer.melevaai.service.interno.PassageiroSituacaoService;
import br.com.cwi.crescer.melevaai.util.PassageiroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepositarCreditoServiceTest {

    @InjectMocks
    private DepositarCreditoService service;

    @Mock
    private PassageiroRepository passageiroRepository;

    @Mock
    private BuscarPassageiroService buscarPassageiroService;

    @Mock
    private PassageiroSituacaoService passageiroSituacaoService;

    @Captor
    private ArgumentCaptor<Passageiro> passageiroCaptor;

    @Test
    @DisplayName("Deve depositar na conta do passageiro com sucesso")
    void deveDepositarNaContaDoPassageiroComSucesso() {
        CreditoRequest request = new CreditoRequest();
        request.setValor(new BigDecimal("100"));
        Passageiro passageiro = PassageiroFactory.get();

        when(buscarPassageiroService.buscarPorId(1L))
                .thenReturn(passageiro);

        service.depositar(1L, request);

        verify(passageiroSituacaoService).verificarAtivo(passageiro);
        verify(buscarPassageiroService).buscarPorId(1L);
        verify(passageiroRepository).save(passageiroCaptor.capture());

        Passageiro passageiroInterno = passageiroCaptor.getValue();
        assertEquals(new BigDecimal("1100.0"),passageiroInterno.getCreditos());
    }
}