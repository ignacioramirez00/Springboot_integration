package com.api.apitest.persistence.integration.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.api.apitest.dto.MyPageable;
import com.api.apitest.persistence.integration.marvel.dto.CharacterDto;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.api.apitest.persistence.integration.MarvelAPIConfig;
import com.api.apitest.persistence.integration.marvel.*;

@Repository
public class CharacterRepository {

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;
    @Autowired
    private HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;
    
    private String characterPath;

    /**
     * Inicializa la URL base para acceder a los personajes en la API de Marvel.
     */
    @PostConstruct
    private void setPath(){
        characterPath = basePath.concat("/").concat("characters");
    }

    
    public List<CharacterDto> findAll(MyPageable myPageable, String name, int[] comics, int[] series) {

        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(myPageable, name, comics, series);
        JsonNode response = httpClientService.doGet(characterPath, marvelQueryParams, JsonNode.class);
        return null;
    }

    private Map<String, String> getQueryParamsForFindAll(MyPageable myPageable, String name, int[] comics,int[] series) {
        
        Map<String, String> queryParams = marvelAPIConfig.getAuthenticationQueryParams();

        if (myPageable != null) {
            queryParams.put("limit", String.valueOf(myPageable.limit()));
            queryParams.put("offset", String.valueOf(myPageable.offset()));
        }

        if (name != null && !name.isEmpty()) {
            queryParams.put("nameStartsWith", name);
        }

        if (comics != null && comics.length > 0) {
            String comicsAsString = joinIntArray(comics);
            queryParams.put("comics",comicsAsString);
        }

        if (series != null && series.length > 0) {
            String seriesAsString = joinIntArray(series);
            queryParams.put("series",seriesAsString);
        }

        return queryParams;
    }

    private String joinIntArray(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public CharacterDto.CharacterInfoDto findById(long characterId) {
        Map<String, String> queryParams = marvelAPIConfig.getAuthenticationQueryParams();
        
        // aca lo que hago es llamar a la url base para hacer la peticion y le agrego el id necesario
        String finalUrl = characterPath.concat("/").concat(Long.toString(characterId));  

        JsonNode response = httpClientService.doGet(finalUrl,queryParams,JsonNode.class);

        return CharacterMapper.toDtoList(response).get(0);
    }
}
