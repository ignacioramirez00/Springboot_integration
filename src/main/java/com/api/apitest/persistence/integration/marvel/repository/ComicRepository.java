package com.api.apitest.persistence.integration.marvel.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.api.apitest.dto.MyPageable;
import com.api.apitest.persistence.integration.MarvelAPIConfig;
import com.api.apitest.persistence.integration.marvel.dto.ComicDto;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.annotation.PostConstruct;

@Repository
public class ComicRepository {

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;
    @Autowired
    private HttpClientService httpClientService;

    @Value("${integration.marvel.base-path}")
    private String basePath;
    
    private String comicPath;

    /**
     * Inicializa la URL base para acceder a los personajes en la API de Marvel.
     */
    @PostConstruct
    private void setPath(){
        comicPath = basePath.concat("/").concat("comics");
    }

    public List<ComicDto> findAll(MyPageable myPageable, long characterId) {
        Map<String,String> marvelQueryParams = marvelAPIConfig.getQueryParamsForFindAll(myPageable, characterId);

        JsonNode response = httpClientService.doGet(comicPath,marvelQueryParams,JsonNode.class);
        return ComicMapper.toDtoList(response);
    }


    public Map<String,String> getQueryParamsForFindAll(MyPageable myPageable, long characterId) {

        Map<String,String> queryParams = marvelAPIConfig.getAuthenticationQueryParams();
        if (myPageable != null) {
            queryParams.put("limit", String.valueOf(myPageable.limit()));
            queryParams.put("offset", String.valueOf(myPageable.offset()));
        }
        
        if (characterId != 0) {
            queryParams.put("characters",Long.toString(characterId));
        }
        return queryParams;
    }

    public ComicDto findById(long comicId) {
        Map<String,String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();
        String finalUrl = comicPath.concat("/").concat(Long.toString(comicId));
        JsonNode response = httpClientService.doGet(finalUrl,marvelQueryParams,JsonNode.class); // llamo a la API
        return ComicMapper.toDtoList(response).get();
    }

    // Tengo que crear los componenetes httpClientService y ComicMapper
}
