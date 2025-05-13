package com.api.apitest.persistence.integration.marvel.dto;

public record ComicDto(
    Long id,
    String title,
    String description,
    String modified,
    String resourceURI,
    ThumbnaiDto thumbnail)
{

}
