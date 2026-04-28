package com.example.dica.domain.receita;

import com.example.dica.domain.estado.Estado;
import com.example.dica.domain.estado.EstadoRepository;
import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Page<Receita> getAll(Pageable pageable, String buscaLivre, TipoRefeicao tipoRefeicao, GrupoAlimentar grupoAlimentar) {
        return receitaRepository.buscarReceitas(buscaLivre, tipoRefeicao, grupoAlimentar, pageable);
    }


    public Receita getById(Long id) {
        return receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));
    }

    public Receita createReceita(ReceitaRequestDto dto) throws IOException {
        var estado = buscarEstado(dto.idEstado());

        var receita = new Receita();
        receita.setTitulo(dto.titulo());
        receita.setTipoRefeicao(dto.tipoRefeicao());
        receita.setTempoPreparoMinutos(dto.tempoPreparoMinutos());
        receita.setPorcao(dto.porcao());
        receita.setGrupoAlimentar(dto.grupoAlimentar());
        receita.setIngredientes(dto.ingredientes());
        receita.setModoPreparo(dto.modoPreparo());
        receita.setRendimento(dto.rendimento());
        receita.setImagem(dto.imagem() != null ? dto.imagem().getBytes() : null);
        receita.setEstado(estado);

        return receitaRepository.save(receita);
    }

    public ReceitaResponseDto updateReceita(Long id, ReceitaUpdateDto dto) throws IOException {
        var receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada, tente outro id"));

        if (dto.titulo() != null) {
            receita.setTitulo(dto.titulo());
        }
        if (dto.tipoRefeicao() != null) {
            receita.setTipoRefeicao(dto.tipoRefeicao());
        }
        if (dto.tempoPreparoMinutos() != null) {
            receita.setTempoPreparoMinutos(dto.tempoPreparoMinutos());
        }
        if (dto.porcao() != null) {
            receita.setPorcao(dto.porcao());
        }
        if (dto.grupoAlimentar() != null) {
            receita.setGrupoAlimentar(dto.grupoAlimentar());
        }
        if (dto.ingredientes() != null) {
            receita.setIngredientes(dto.ingredientes());
        }
        if (dto.modoPreparo() != null) {
            receita.setModoPreparo(dto.modoPreparo());
        }
        if (dto.rendimento() != null) {
            receita.setRendimento(dto.rendimento());
        }
        if (dto.imagem() != null) {
            receita.setImagem(dto.imagem().getBytes());
        }
        if (dto.idEstado() != null) {
            receita.setEstado(buscarEstado(dto.idEstado()));
        }

        receitaRepository.save(receita);
        return new ReceitaResponseDto(receita);
    }

    public void deleteReceita(Long id) {
        if (!receitaRepository.existsById(id)) {
            throw new RuntimeException("Receita não encontrada, tente outro id");
        }
        receitaRepository.deleteById(id);
    }

    private Estado buscarEstado(Long idEstado) {
        if (idEstado == null) {
            return null;
        }
        return estadoRepository.findById(idEstado)
                .orElseThrow(() -> new RuntimeException("Estado não encontrado, tente outro id"));
    }
}

