package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.controller.request.CreditoRequest;
import br.com.cwi.crescer.melevaai.controller.response.BuscarPassageiroResponse;
import br.com.cwi.crescer.melevaai.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/passageiro")
public class PassageiroController {

    @Autowired
    private ListarPassageiroService listarPassageiroService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private DepositarCreditoService depositarCreditoService;

    @Autowired
    private DesativarService desativarService;

    @Autowired
    private GetPassageiroService getPassageiroService;

    @GetMapping()
    public List<BuscarPassageiroResponse> listar() {
        return listarPassageiroService.listar();
    }

    @GetMapping("/{id}")
    public BuscarPassageiroResponse buscar(@PathVariable Long id) {
        return getPassageiroService.buscar(id);
    }

    @PutMapping("/{id}/depositar")
    public void depositar(@PathVariable Long id, @Valid @RequestBody CreditoRequest request) {
        depositarCreditoService.depositar(id, request);
    }
    @PutMapping("/{id}/inativar")
    public void inativar(@PathVariable Long id){
        desativarService.desativarPassageiro(id);
    }


}
