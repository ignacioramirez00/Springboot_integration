package com.api.apitest.services.impl;

import com.api.apitest.exception.ApiErrorException;
import com.api.apitest.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

/**
 * La clase RestTemplateService implementa un servicio HTTP utilizando Spring RestTemplate.
 * Proporciona métodos para realizar solicitudes HTTP GET, POST, PUT y DELETE.
 */
@Service
public class RestTemplateService implements HttpClientService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Realiza una solicitud HTTP GET.
     *
     * @param endpoint     La URL de destino de la solicitud.
     * @param queryParams  Parámetros de consulta para la solicitud.
     * @param responseType  El tipo de respuesta esperado.
     * @param <T>          El tipo de respuesta.
     * @return La respuesta de la solicitud.
     * @throws ApiErrorException si ocurre un error en la solicitud.
     */
    @Override
    public <T> T doGet(String endpoint, Map<String, String> queryParams, Class<T> responseType) {
        URI finalUrl = buildFinalUri(endpoint, queryParams);

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
        System.out.println("URL = " + finalUrl);
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.GET, httpEntity, responseType);

        if(response.getStatusCode().value() != HttpStatus.OK.value()){
            String message = String.format("Error consumiendo endpoint [ %s - %s ], código de respuesta es: %s",
                    HttpMethod.GET, endpoint, response.getStatusCode());
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }

    /**
     * Construye la URI final con parámetros de consulta.
     *
     * @param endpoint     La URL base de destino.
     * @param queryParams  Parámetros de consulta.
     * @return La URI final con los parámetros de consulta aplicados.
     */
    private URI buildFinalUri(String endpoint, Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(endpoint);

        if(queryParams != null){
            for(Map.Entry<String, String> entry : queryParams.entrySet()){
                builder.queryParam(entry.getKey(), entry.getValue());
            }
        }

        return builder.build().toUri();
    }

    /**
     * Obtiene los encabezados HTTP para las solicitudes.
     *
     * @return Los encabezados HTTP configurados.
     */
    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Si necesitas enviar un token JWT, descomenta la siguiente línea
        // headers.add("Authorization", "Bearer jwt");
        return headers;
    }

    /**
     * Realiza una solicitud HTTP POST.
     *
     * @param endpoint     La URL de destino de la solicitud.
     * @param queryParams  Parámetros de consulta para la solicitud.
     * @param responseType  El tipo de respuesta esperado.
     * @param bodyRequest  El cuerpo de la solicitud.
     * @param <T>          El tipo de respuesta.
     * @param <R>          El tipo del cuerpo de la solicitud.
     * @return La respuesta de la solicitud.
     * @throws ApiErrorException si ocurre un error en la solicitud.
     */
    @Override
    public <T, R> T doPost(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        URI finalUrl = buildFinalUri(endpoint, queryParams);

        HttpEntity<R> httpEntity = new HttpEntity<>(bodyRequest, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.POST, httpEntity, responseType);

        if(response.getStatusCode().value() != HttpStatus.OK.value() && response.getStatusCode().value() != HttpStatus.CREATED.value()){
            String message = String.format("Error consumiendo endpoint [ %s - %s ], código de respuesta es: %s",
                    HttpMethod.POST, endpoint, response.getStatusCode());
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }

    /**
     * Realiza una solicitud HTTP PUT.
     *
     * @param endpoint     La URL de destino de la solicitud.
     * @param queryParams  Parámetros de consulta para la solicitud.
     * @param responseType  El tipo de respuesta esperado.
     * @param bodyRequest  El cuerpo de la solicitud.
     * @param <T>          El tipo de respuesta.
     * @param <R>          El tipo del cuerpo de la solicitud.
     * @return La respuesta de la solicitud.
     * @throws ApiErrorException si ocurre un error en la solicitud.
     */
    @Override
    public <T, R> T doPut(String endpoint, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        URI finalUrl = buildFinalUri(endpoint, queryParams);

        HttpEntity<R> httpEntity = new HttpEntity<>(bodyRequest, getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.PUT, httpEntity, responseType);

        if(response.getStatusCode().value() != HttpStatus.OK.value()){
            String message = String.format("Error consumiendo endpoint [ %s - %s ], código de respuesta es: %s",
                    HttpMethod.PUT, endpoint, response.getStatusCode());
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }

    /**
     * Realiza una solicitud HTTP DELETE.
     *
     * @param endpoint     La URL de destino de la solicitud.
     * @param queryParams  Parámetros de consulta para la solicitud.
     * @param responseType  El tipo de respuesta esperado.
     * @param <T>          El tipo de respuesta.
     * @return La respuesta de la solicitud.
     * @throws ApiErrorException si ocurre un error en la solicitud.
     */
    @Override
    public <T> T doDelete(String endpoint, Map<String, String> queryParams, Class<T> responseType) {
        URI finalUrl = buildFinalUri(endpoint, queryParams);

        HttpEntity<?> httpEntity = new HttpEntity<>(getHeaders());
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.DELETE, httpEntity, responseType);

        if(response.getStatusCode().value() != HttpStatus.OK.value()){
            String message = String.format("Error consumiendo endpoint [ %s - %s ], código de respuesta es: %s",
                    HttpMethod.DELETE, endpoint, response.getStatusCode());
            throw new ApiErrorException(message);
        }

        return response.getBody();
    }
}