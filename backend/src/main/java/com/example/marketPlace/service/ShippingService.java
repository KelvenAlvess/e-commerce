package com.example.marketPlace.service;

import com.example.marketPlace.dto.ShippingOptionDTO;
import com.example.marketPlace.dto.melhorenvio.MelhorEnvioRequestDTO;
import com.example.marketPlace.dto.melhorenvio.MelhorEnvioResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingService {

    @Value("${melhorenvio.url}")
    private String apiUrl;

    @Value("${melhorenvio.token}")
    private String apiToken;

    @Value("${melhorenvio.cep.origem}")
    private String cepOrigem;

    private final ObjectMapper objectMapper;

    public List<ShippingOptionDTO> calculateOptions(String cepDestino) {
        String cleanCep = cepDestino.replace("-", "").trim();
        List<ShippingOptionDTO> options = new ArrayList<>();

        if (cleanCep.length() != 8) return options;

        try {

            MelhorEnvioRequestDTO requestPayload = createPayload(cleanCep);
            String jsonBody = objectMapper.writeValueAsString(requestPayload);

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + apiToken) // Autenticação
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {

                List<MelhorEnvioResponseDTO> meOptions = objectMapper.readValue(
                        response.body(),
                        new TypeReference<List<MelhorEnvioResponseDTO>>() {}
                );

                for (MelhorEnvioResponseDTO meOption : meOptions) {
                    if (meOption.error() != null) continue; // Pula opções indisponíveis

                    String carrierName = meOption.company().name();
                    String serviceName = meOption.name() + " (" + carrierName + ")";

                    options.add(new ShippingOptionDTO(
                            serviceName,
                            BigDecimal.valueOf(meOption.customPrice()), // Preço final com desconto
                            meOption.deliveryTime()
                    ));
                }
            } else {
                log.error("Erro Melhor Envio: " + response.body());
            }

        } catch (Exception e) {
            log.error("Falha ao calcular frete no Melhor Envio", e);
        }

        return options;
    }

    private MelhorEnvioRequestDTO createPayload(String cepDestino) {

        var from = new MelhorEnvioRequestDTO.Location(cepOrigem);
        var to = new MelhorEnvioRequestDTO.Location(cepDestino);

        var produtoExemplo = new MelhorEnvioRequestDTO.ProductPayload(
                "ID_PRODUTO",
                20,
                20,
                20,
                1.0,
                50.0,
                1
        );

        return new MelhorEnvioRequestDTO(from, to, Collections.singletonList(produtoExemplo));
    }
}