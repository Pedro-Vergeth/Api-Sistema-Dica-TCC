package com.example.dica.domain.alimento;

import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    public Page<Alimento> getAll(Pageable pageable) {
        
        return alimentoRepository.findAll(pageable);
    }

    public Alimento getById(Long id) {
        return alimentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Alimento não encontrado"));
    }

    public List<Alimento> getRandonly(){
        return alimentoRepository.getAlimentoRandomly();
    }

    public Page<Alimento> getAll(Pageable pageable, GrupoAlimentar grupoAlimentar) {
        if (grupoAlimentar != null) {
            return alimentoRepository.findByGrupoAlimentar(grupoAlimentar, pageable);
        }
        return alimentoRepository.findAll(pageable);
    }

    public Alimento createAlimento(AlimentoRequestDto dto) throws IOException {
        return alimentoRepository.save(new Alimento(
                null,
                dto.nomePrincipal(),
                dto.sinonimos(),
                dto.porcao(),
                dto.medidaCaseira(),
                dto.textoInformativo(),
                dto.imagem() != null ? dto.imagem().getBytes() : null,
                dto.grupoAlimentar()
        ));
    }

    public Page<Alimento> buscarPorBuscaLivre(String termo, Pageable pageable) {

        if (termo == null || termo.trim().isEmpty()) {
            return getAll(pageable);
        }
        return alimentoRepository.buscarPorNomeOuSinonimo(termo, pageable);
    }


    public AlimentoResponseDto updateAlimento(Long id, AlimentoRequestDto dto) throws IOException {
        var alimento = alimentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alimento não encontrado, tente outro id"));

        if (dto.nomePrincipal() != null) {
            alimento.setNomePrincipal(dto.nomePrincipal());
        }
        if (dto.sinonimos() != null) {
            alimento.setSinonimos(dto.sinonimos());
        }
        if (dto.porcao() != null) {
            alimento.setPorcao(dto.porcao());
        }
        if (dto.medidaCaseira() != null) {
            alimento.setMedidaCaseira(dto.medidaCaseira());
        }
        if (dto.textoInformativo() != null) {
            alimento.setTextoInformativo(dto.textoInformativo());
        }
        if (dto.imagem() != null) {
            alimento.setImagem(dto.imagem().getBytes());
        }
        if (dto.grupoAlimentar() != null) {
            alimento.setGrupoAlimentar(dto.grupoAlimentar());
        }

        alimentoRepository.save(alimento);
        return new AlimentoResponseDto(alimento);
    }

    public void deleteAlimento(Long id) {
        if (!alimentoRepository.existsById(id)) {
            throw new RuntimeException("Alimento não encontrado, tente outro id");
        }
        alimentoRepository.deleteById(id);
    }
}
