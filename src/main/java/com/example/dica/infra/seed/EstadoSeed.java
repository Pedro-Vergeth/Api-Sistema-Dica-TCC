package com.example.dica.infra.seed;

import com.example.dica.domain.estado.Estado;
import com.example.dica.domain.estado.EstadoRepository;
import com.example.dica.domain.estado.Regiao;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class EstadoSeed {

    private final EstadoRepository estadoRepository;

    public EstadoSeed(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void seedEstados() {
        List<Estado> estadosParaSalvar = new ArrayList<>();

        adicionarSeNaoExistir(estadosParaSalvar, "Acre", "AC", Regiao.NORTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Alagoas", "AL", Regiao.NORDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Amapá", "AP", Regiao.NORTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Amazonas", "AM", Regiao.NORTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Bahia", "BA", Regiao.NORDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Ceará", "CE", Regiao.NORDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Distrito Federal", "DF", Regiao.CENTRO_OESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Espírito Santo", "ES", Regiao.SUDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Goiás", "GO", Regiao.CENTRO_OESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Maranhão", "MA", Regiao.NORDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Mato Grosso", "MT", Regiao.CENTRO_OESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Mato Grosso do Sul", "MS", Regiao.CENTRO_OESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Minas Gerais", "MG", Regiao.SUDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Pará", "PA", Regiao.NORTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Paraíba", "PB", Regiao.NORDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Paraná", "PR", Regiao.SUL);
        adicionarSeNaoExistir(estadosParaSalvar, "Pernambuco", "PE", Regiao.NORDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Piauí", "PI", Regiao.NORDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Rio de Janeiro", "RJ", Regiao.SUDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Rio Grande do Norte", "RN", Regiao.NORDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Rio Grande do Sul", "RS", Regiao.SUL);
        adicionarSeNaoExistir(estadosParaSalvar, "Rondônia", "RO", Regiao.NORTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Roraima", "RR", Regiao.NORTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Santa Catarina", "SC", Regiao.SUL);
        adicionarSeNaoExistir(estadosParaSalvar, "São Paulo", "SP", Regiao.SUDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Sergipe", "SE", Regiao.NORDESTE);
        adicionarSeNaoExistir(estadosParaSalvar, "Tocantins", "TO", Regiao.NORTE);

        if (!estadosParaSalvar.isEmpty()) {
            estadoRepository.saveAll(estadosParaSalvar);
        }
    }

    private void adicionarSeNaoExistir(List<Estado> estadosParaSalvar, String nome, String sigla, Regiao regiao) {
        if (estadoRepository.findBySigla(sigla).isPresent()) {
            return;
        }

        Estado estado = new Estado();
        estado.setNome(nome);
        estado.setSigla(sigla);
        estado.setRegiao(regiao);
        estadosParaSalvar.add(estado);
    }
}


