package com.example.dica.domain.alimento;

import com.example.dica.domain.estado.Estado;
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
    private String porcao;

    @Column(name = "medida_caseira", length = 100)
    private String medidaCaseira;

    @Column(name = "texto_informativo", columnDefinition = "TEXT")
    private String textoInformativo;

    @Column(name = "imagem", columnDefinition = "bytea")
    private byte[] imagem;

    @Enumerated(EnumType.STRING)
    @Column(name = "grupo_alimentar", nullable = false, length = 20)
    private GrupoAlimentar grupoAlimentar;
}
