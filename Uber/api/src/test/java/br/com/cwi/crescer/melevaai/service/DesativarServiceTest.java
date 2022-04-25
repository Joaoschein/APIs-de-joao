package br.com.cwi.crescer.melevaai.service;

import br.com.cwi.crescer.melevaai.model.Motorista;
import br.com.cwi.crescer.melevaai.model.Passageiro;
import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import br.com.cwi.crescer.melevaai.repository.MotoristaRepository;
import br.com.cwi.crescer.melevaai.repository.PassageiroRepository;
import br.com.cwi.crescer.melevaai.service.interno.MotoristaSituacaoService;
import br.com.cwi.crescer.melevaai.service.interno.PassageiroSituacaoService;
import br.com.cwi.crescer.melevaai.util.MotoristaFactory;
import br.com.cwi.crescer.melevaai.util.PassageiroFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DesativarServiceTest {

    @InjectMocks
    private DesativarService service;

    @Mock
    private MotoristaRepository motoristaRepository;

    @Mock
    private PassageiroRepository passageiroRepository;

    @Mock
    private BuscarMotoristaService buscarMotoristaService;

    @Mock
    private BuscarPassageiroService buscarPassageiroService;

    @Mock
    private MotoristaSituacaoService motoristaSituacaoService;

    @Mock
    private PassageiroSituacaoService passageiroSituacaoService;

    @Captor
    private ArgumentCaptor<Motorista> motoristaCaptor;

    @Captor
    private ArgumentCaptor<Passageiro> passageiroCaptor;

    @Test
    @DisplayName("Deve alterar situacao do motorista para desativado")
    void deveAlterarMotoristaParaDesativado() {
        Motorista motorista = MotoristaFactory.get();

        when(buscarMotoristaService.buscarPorId(1L))
                .thenReturn(motorista);

        service.desativarMotorista(1L);

        verify(buscarMotoristaService).buscarPorId(1L);
        verify(motoristaSituacaoService).verificarLivre(motorista);
        verify(motoristaRepository).save(motoristaCaptor.capture());

        Motorista motoristaInterno = motoristaCaptor.getValue();
        assertEquals(SituacaoUsuario.INATIVO, motoristaInterno.getSituacaoUsuario());
    }

    @Test
    @DisplayName("Deve alterar situacao do passageiro para desativado")
    void deveAlterarPassageiroParaDesativado() {
        Passageiro passageiro = PassageiroFactory.get();

        when(buscarPassageiroService.buscarPorId(1L))
                .thenReturn(passageiro);

        service.desativarPassageiro(1L);

        verify(buscarPassageiroService).buscarPorId(1L);
        verify(passageiroSituacaoService).verificarLivre(passageiro);
        verify(passageiroRepository).save(passageiroCaptor.capture());

        Passageiro passaGeiroInterno = passageiroCaptor.getValue();
        assertEquals(SituacaoUsuario.INATIVO, passaGeiroInterno.getSituacaoUsuario());
    }
}