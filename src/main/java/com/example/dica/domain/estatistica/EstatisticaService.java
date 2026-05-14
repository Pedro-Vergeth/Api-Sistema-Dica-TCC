package com.example.dica.domain.estatistica;

import com.example.dica.domain.alimento.AlimentoRepository;
import com.example.dica.domain.receita.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;

@Service
public class EstatisticaService {

    private static final ZoneId ZONA_BRASIL = ZoneId.of("America/Sao_Paulo");

    @Autowired
    private EstatisticaPesquisaRepository estatisticaPesquisaRepository;

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Transactional
    public EstatisticaResponseDto getResumo() {
        var agora = YearMonth.now(ZONA_BRASIL);
        return getResumo(agora.getYear(), agora.getMonthValue());
    }

    @Transactional
    public EstatisticaResponseDto getResumo(Integer ano, Integer mes) {
        var periodo = resolverPeriodo(ano, mes);
        var inicio = periodo.atDay(1).atStartOfDay();
        var fim = periodo.atEndOfMonth().atTime(23, 59, 59, 999_999_999);

        return new EstatisticaResponseDto(
                periodo.getYear(),
                periodo.getMonthValue(),
                contarPorTipo(TipoPesquisaEstatistica.ALIMENTO, inicio, fim),
                contarPorTipo(TipoPesquisaEstatistica.RECEITA, inicio, fim),
                contarPorTipo(TipoPesquisaEstatistica.VIDEO_EDUCATIVO, inicio, fim),
                alimentoRepository.count(),
                receitaRepository.count()
        );
    }

    @Transactional
    public void registrarPesquisaAlimentos() {
        registrarPesquisa(TipoPesquisaEstatistica.ALIMENTO);
    }

    @Transactional
    public void registrarPesquisaReceitas() {
        registrarPesquisa(TipoPesquisaEstatistica.RECEITA);
    }

    @Transactional
    public void registrarPesquisaVideosEducativos() {
        registrarPesquisa(TipoPesquisaEstatistica.VIDEO_EDUCATIVO);
    }

    private void registrarPesquisa(TipoPesquisaEstatistica tipoPesquisa) {
        estatisticaPesquisaRepository.save(new EstatisticaPesquisa(null, tipoPesquisa, LocalDateTime.now(ZONA_BRASIL)));
    }

    private long contarPorTipo(TipoPesquisaEstatistica tipoPesquisa, LocalDateTime inicio, LocalDateTime fim) {
        return estatisticaPesquisaRepository.countByTipoPesquisaAndDataPesquisaBetween(tipoPesquisa, inicio, fim);
    }

    private YearMonth resolverPeriodo(Integer ano, Integer mes) {
        if (ano == null && mes == null) {
            return YearMonth.now(ZONA_BRASIL);
        }

        if (ano == null || mes == null) {
            throw new IllegalArgumentException("Informe ano e mês juntos ou não informe nenhum para usar o mês atual");
        }

        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mês inválido, tente um valor entre 1 e 12");
        }

        return YearMonth.of(ano, mes);
    }
}


