package br.com.cwi.crescer.melevaai.controller;

import br.com.cwi.crescer.melevaai.controller.request.AvaliacaoRequest;
import br.com.cwi.crescer.melevaai.controller.request.SolicitarCorridaRequest;
import br.com.cwi.crescer.melevaai.controller.response.IniciarCorridaResponse;
import br.com.cwi.crescer.melevaai.controller.response.SolicitarCorridaResponse;
import br.com.cwi.crescer.melevaai.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/corrida")
public class CorridaController {

    @Autowired
    private SolicitarCorridaService solicitarCorridaService;

    @Autowired
    private IniciarCorridaService iniciarCorridaService;

    @Autowired
    private FinalizarCorridaService finalizarCorridaService;

    @Autowired
    private ListarMotoristaService listarMotoristaService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping()
    public SolicitarCorridaResponse solicitar(@RequestBody SolicitarCorridaRequest request) {
        return solicitarCorridaService.solicitar(request);
    }

    @PutMapping("/{id}/iniciar")
    public IniciarCorridaResponse iniciar(@PathVariable Long id) {
        return iniciarCorridaService.iniciar(id);
    }

    @PutMapping("/{id}/finalizar")
    public void finalizar(@PathVariable Long id) {
        finalizarCorridaService.finalizar(id);
    }

    @PutMapping("/{id}/avaliar/motorista")
    public void avaliarMotorista(@PathVariable Long id, @Valid @RequestBody AvaliacaoRequest request) {
        avaliacaoService.avaliarMotorista(id, request);
    }

    @PutMapping("/{id}/avaliar/passageiro")
    public void avaliarPassageiro(@PathVariable Long id, @Valid @RequestBody AvaliacaoRequest request) {
        avaliacaoService.avaliarPassageiro(id, request);
    }
}
