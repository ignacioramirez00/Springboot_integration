package com.api.apitest.persistence.integration.marvel.dto;

public record CharacterDto(Long id,
        String name,
        String description,
        String modified,
        String resourceURI) {

    public record CharacterInfoDto(
        String imagePathString, 
        String description) 
        {

        }
}
