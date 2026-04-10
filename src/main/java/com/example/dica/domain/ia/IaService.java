package com.example.dica.domain.ia;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class IaService {

    @Value("${ollama.api.url}")
    private String ollamaUrl;

    @Value("${ollama.api.model}")
    private String ollamaModel;

    @Value("${ollama.api.prompt}")
    private String ollamaPrompt;

    private final RestTemplate restTemplate = new RestTemplate();

    public IaResponseDto identifyFoodByImage(String image64) {
        if (image64 == null || image64.isBlank()) {
            throw new IllegalArgumentException("A imagem em base64 não pode ser vazia");
        }

        var base64 = limparBase64(image64);

        var payload = Map.of(
                "model", ollamaModel,
                "prompt", ollamaPrompt,
                "images", List.of(base64),
                "stream", false
        );

        ResponseEntity<IaResponseDto> response = restTemplate.postForEntity(ollamaUrl, payload, IaResponseDto.class);

        if (response.getBody() == null || response.getBody().response() == null || response.getBody().response().isBlank()) {
            throw new RuntimeException("O Ollama não retornou um nome de alimento");
        }

        return new IaResponseDto(response.getBody().response().trim());
    }

    private String limparBase64(String image64) {
        var valor = image64.trim();
        if (valor.startsWith("data:")) {
            var indice = valor.indexOf(',');
            if (indice >= 0 && indice < valor.length() - 1) {
                return valor.substring(indice + 1);
            }
        }
        return valor;
    }
}
