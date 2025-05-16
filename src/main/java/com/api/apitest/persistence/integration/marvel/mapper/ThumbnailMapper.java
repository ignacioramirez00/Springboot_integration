package com.api.apitest.persistence.integration.marvel.mapper;

import com.api.apitest.persistence.integration.marvel.dto.ThumbnailDto;
import com.fasterxml.jackson.databind.JsonNode;

public class ThumbnailMapper {

    public static ThumbnailDto toDto(JsonNode response) {
        if (response == null) {
            throw new IllegalArgumentException("The node cannot be null");
        }
        String path = response.get("path").asText();
        String extension = response.get("extension").asText();
        return new ThumbnailDto(path, extension);
    }

}
