package br.com.cwi.crescer.oldflix.controller;


import br.com.cwi.crescer.oldflix.controller.request.AlugarFilmeRequest;
import br.com.cwi.crescer.oldflix.controller.request.DevolverFilmeRequest;
import br.com.cwi.crescer.oldflix.controller.request.IncluirFilmeRequest;
import br.com.cwi.crescer.oldflix.controller.response.*;
import br.com.cwi.crescer.oldflix.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;


    @GetMapping
    public List<ListarFilmeResponse> listar() {
        return filmeService.listar();
    }

    @PostMapping
    public IncluirFilmeResponse incluir(@RequestBody IncluirFilmeRequest request) {
        return filmeService.incluir(request);
    }

    @PutMapping("/{id}/alugar")
    public AlugarFilmeResponse alugar (@RequestBody AlugarFilmeRequest request, @PathVariable Long id) {
        return filmeService.alugar(request, id);
    }

    @PutMapping("/{id}/devolver")
    public DevolverFilmeResponse devolver (@RequestBody DevolverFilmeRequest request, @PathVariable Long id) {
        return filmeService.devolver(request, id);
    }

    @GetMapping("{id}")
    public DetalharFilmeResponse detalhar(@PathVariable Long id) {
        return filmeService.detalhar(id);
    }

}


