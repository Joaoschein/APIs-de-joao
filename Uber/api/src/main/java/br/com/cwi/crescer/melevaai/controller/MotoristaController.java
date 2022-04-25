package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.controller.request.CreditoRequest;
import br.com.cwi.crescer.melevaai.controller.response.BuscarMotoristaResponse;
import br.com.cwi.crescer.melevaai.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/motorista")
public class MotoristaController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private SacarCreditoService sacarCreditoService;

    @Autowired
    private ListarMotoristaService listarMotoristaService;

    @Autowired
    private DesativarService desativarService;

    @Autowired
    private GetMotoristaService getMotoristaService;

    @GetMapping()
    public List<BuscarMotoristaResponse> listar() {
        return listarMotoristaService.listar();
    }

    @GetMapping("/{id}")
    public BuscarMotoristaResponse buscar(@PathVariable Long id) {
        return getMotoristaService.buscar(id);
    }

    @PutMapping("/{id}/sacar")
    public void sacar(@PathVariable Long id, @Valid @RequestBody CreditoRequest request) {
        sacarCreditoService.sacar(id, request);
    }

    @PutMapping("/{id}/inativar")
    public void inativar(@PathVariable Long id){
        desativarService.desativarMotorista(id);
    }
}