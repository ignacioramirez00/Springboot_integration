package com.api.apitest.persistence.integration.marvel.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.apitest.dto.MyPageable;
import com.api.apitest.persistence.integration.MarvelAPIConfig;
import com.api.apitest.persistence.integration.marvel.dto.CharacterDto;
import com.api.apitest.persistence.integration.marvel.dto.CharacterDto.CharacterInfoDto;
import com.fasterxml.jackson.databind.JsonNode;

@Repository
public class CharacterRepository {

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;

    @Override
    public List<CharacterDto> findAll(MyPageable myPageable, String name, int[] comics, int[] series) {

        Map<String, String> marvelQueryParams = getQueryParamsForFindAll(myPageable, name, comics, series);
        JsonNode response = httpClientService.doGet(characterPath, marvelQueryParams, JsonNode.class);
        return null;
    }

    private Map<String, String> getQueryParamsForFindAll(MyPageable myPageable, String name, int[] comics,int[] series) {
        
        Map<String, String> queryParams = marvelAPIConfig.getAuthenticationQueryParams();
        
        // Agregar parámetros de paginación
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

    @Override
    public CharacterInfoDto findById(long characterId) {
        return null;
    }
}
