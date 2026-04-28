package com.example.dica.domain.videoEducativo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "video_educativo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(name = "duracao_segundos")
    private Integer duracaoSegundos;

    @Column(length = 255)
    private String descricao;

    @Column(name = "video_url", nullable = false, length = 255)
    private String videoUrl;
}

