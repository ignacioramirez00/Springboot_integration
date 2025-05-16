package com.api.apitest.persistence.integration.marvel.mapper;

import java.util.ArrayList;
import java.util.List;

import com.api.apitest.persistence.integration.marvel.dto.CharacterDto;
import com.api.apitest.persistence.integration.marvel.dto.ThumbnailDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class CharacterMapper {

    // esto es lo que va a hacer el mapper con lo que recibe de la api   MAPEADOR DE JSON A DTOS
    public static List<CharacterDto> toDtoList(JsonNode response) {

        ArrayNode results = getResultsNode(response);

        List<CharacterDto> characters = new ArrayList<>();
        results.elements().forEachRemaining(each -> {
            characters.add(CharacterMapper.toDto(each));
        });

        return characters;
    }

    private static CharacterDto toDto(JsonNode characterNode) {
        if (characterNode == null) {
            throw new IllegalArgumentException("The node cannot be null");
        }
        CharacterDto dto = new CharacterDto(
                Long.parseLong(characterNode.get("id").asText()),
                characterNode.get("name").asText(),
                characterNode.get("description").asText(),
                characterNode.get("modified").asText(),
                characterNode.get("resourceURI").asText());

        return dto;
        // de esta forma se mapea con la estructura del dto
    }

    private static ArrayNode getResultsNode(JsonNode response) {
        if (response == null) {
            throw new IllegalArgumentException("The node cannot be null");
        }
        JsonNode data = response.get("data");
        ArrayNode results = (ArrayNode) data.get("results");
        return results;
    }

    public static List<CharacterDto.CharacterInfoDto> toInfoDtoList(JsonNode response) {
        List<CharacterDto.CharacterInfoDto> characters = new ArrayList<>();
        ArrayNode results = getResultsNode(response);
        results.elements().forEachRemaining(each -> {
            characters.add(CharacterMapper.toInfoDto(each));
        });
        return characters;
    }

    private static CharacterDto.CharacterInfoDto toInfoDto(JsonNode characterNode) {
        
        if (characterNode == null) {
            throw new IllegalArgumentException("The node cannot be null");
        }

        ThumbnailDto thumbnail =   ThumbnailMapper.toDto(characterNode.get("thumbnail"));
        String image = thumbnail.path() + "." + thumbnail.extension();

        
        CharacterDto.CharacterInfoDto dto = new CharacterDto.CharacterInfoDto(
                image,
                characterNode.get("description").asText());
        return dto;
    }

}
