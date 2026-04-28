package com.example.dica.domain.receita;

import com.example.dica.domain.estado.Estado;
import com.example.dica.domain.grupoAlimentar.GrupoAlimentar;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "receitas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_refeicao", nullable = false, length = 50)
    private TipoRefeicao tipoRefeicao;

    @Column(name = "tempo_preparo_minutos")
    private Integer tempoPreparoMinutos;

    @Column(length = 50)
    private String porcao;

    @Enumerated(EnumType.STRING)
    @Column(name = "grupo_alimentar", nullable = false, length = 20)
    private GrupoAlimentar grupoAlimentar;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String ingredientes;

    @Column(name = "modo_preparo", columnDefinition = "TEXT", nullable = false)
    private String modoPreparo;

    @Column(nullable = false, length = 100)
    private String rendimento;

    @Column(name = "imagem", columnDefinition = "bytea")
    private byte[] imagem;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private Estado estado;
}


