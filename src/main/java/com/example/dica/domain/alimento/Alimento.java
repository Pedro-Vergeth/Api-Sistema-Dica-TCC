package com.example.dica.domain.alimento;

import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alimentos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_principal", nullable = false, length = 100)
    private String nomePrincipal;

    @Column(columnDefinition = "TEXT")
    private String sinonimos;

    @Column(length = 50)
    private String unidade;

    @Column(name = "unidade_medida_caseira", length = 100)
    private String unidadeMedidaCaseira;

    @Column(name = "qtd_para_um_coracao", nullable = false)
    private Double qtdParaUmCoracao;

    @Column(name = "qtd_medida_caseira")
    private Double qtdMedidaCaseira;

    @Column(name = "texto_informativo", columnDefinition = "TEXT")
    private String textoInformativo;

    @Column(name = "imagem", columnDefinition = "bytea")
    private byte[] imagem;


    @Enumerated(EnumType.STRING)
    @Column(name = "grupo_alimentar", nullable = false, length = 20)
    private GrupoAlimentar grupoAlimentar;
}
