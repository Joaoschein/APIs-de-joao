package br.com.cwi.crescer.oldflix.service;

import br.com.cwi.crescer.oldflix.Situacao;
import br.com.cwi.crescer.oldflix.controller.request.AlugarFilmeRequest;
import br.com.cwi.crescer.oldflix.controller.request.DevolverFilmeRequest;
import br.com.cwi.crescer.oldflix.controller.request.IncluirFilmeRequest;
import br.com.cwi.crescer.oldflix.controller.response.*;
import br.com.cwi.crescer.oldflix.mapper.*;
import br.com.cwi.crescer.oldflix.model.Filme;
import br.com.cwi.crescer.oldflix.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmeService {

    @Autowired
    private ListarFilmeMapper listarFilmeMapper;

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private IncluirFilmeMapper incluirFilmeMapper;

    @Autowired
    private AlugarFilmeMapper alugarFilmeMapper;

    @Autowired
    private DevolverFilmeMapper devolverFilmeMapper;

    @Autowired
    private DetalharFilmeMapper detalharFilmeMapper;


    public List<ListarFilmeResponse> listar() {
        return filmeRepository.findAll().stream()
                .map(p -> listarFilmeMapper.toResponse(p))
                .collect(Collectors.toList());
    }

    public IncluirFilmeResponse incluir(IncluirFilmeRequest request) {

        // converte o request (dto) para uma entidade
        Filme filme = incluirFilmeMapper.toEntity(request);

        // persiste
        filmeRepository.save(filme);

        // converte entidade para um response (dto) e retorna
        return incluirFilmeMapper.toResponse(filme);
    }

    public AlugarFilmeResponse alugar(AlugarFilmeRequest request, Long id) {
        //pega o filme do repositorio
        Filme filme = filmeRepository.getById(id);

        if(filme.isDisponivel() ) {

            filme.setDisponivel(false);
            filme.setResponsavel(request.getResponsavel());
            filme.setDataDeRetirada(LocalDate.now());
        }

        filmeRepository.save(filme);

        return alugarFilmeMapper.toResponse(filme);
    }


    public DevolverFilmeResponse devolver(DevolverFilmeRequest request, Long id) {
        Filme filme = filmeRepository.getById(id);

        if (filme.getResponsavel().equals(request.getResponsavel())) {
            filme.setDisponivel(true);
            filme.setResponsavel("");
            filme.setDataDeRetirada(null);
        }

        filmeRepository.save(filme);

        return devolverFilmeMapper.toResponse(filme);

    }

    public DetalharFilmeResponse detalhar(Long id) {
        Filme filme = filmeRepository.getById(id);
        DetalharFilmeResponse filmeResponse = detalharFilmeMapper.toResponse(filme);

        if(!filmeResponse.isDisponivel()) {
            LocalDate calculoDataDeEntrega = filmeResponse.getDataDeRetirada().plusDays(filmeResponse.getCategoria().getDescricao());
            filmeResponse.setDataDeEntrega(calculoDataDeEntrega);
        }

        boolean temDataDeEntrega = filmeResponse.getDataDeEntrega()!=null;
        if(temDataDeEntrega) {
            if(filmeResponse.getDataDeEntrega().isBefore(LocalDate.now()) && temDataDeEntrega){
                filmeResponse.setSitucao(Situacao.EM_ATRASO);
            } else if(filmeResponse.getDataDeEntrega().isAfter(LocalDate.now()) && temDataDeEntrega) {
                filmeResponse.setSitucao(Situacao.EM_DIA);
            }
        }

        return filmeResponse;

    }

}
