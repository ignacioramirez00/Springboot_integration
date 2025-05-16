package com.api.apitest.persistence.integration.marvel.mapper;

import java.util.ArrayList;
import java.util.List;

import com.api.apitest.persistence.integration.marvel.dto.ComicDto;
import com.api.apitest.persistence.integration.marvel.dto.ThumbnailDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ComicMapper {


        /**
     * Convierte un objeto JsonNode que representa una lista de cómics en una lista de objetos ComicDto.
     *
     * @param rootNode El nodo JsonNode que contiene la lista de cómics.
     * @return Una lista de objetos ComicDto que representan los cómics.
     * @throws IllegalArgumentException Si el nodo JsonNode es nulo.
     */
    public static List<ComicDto> toDtoList(JsonNode response) {
        ArrayNode results = getResultsNode(response); // me traigo los resultados

        List<ComicDto> comics = new ArrayList<>(); // creo una lista de comics
        results.elements().forEachRemaining(each -> {
            comics.add(ComicMapper.toDto(each)); // agrego cada comic a la lista
        });

        return null;
    }

    // por cada elemento de la lista de comics voy a generar un comicDto
    private static ComicDto toDto(JsonNode comicNode) {
        if (comicNode == null) {
            throw new IllegalArgumentException("The node cannot be null");
        }

        ThumbnailDto thumbnail = ThumbnailMapper.toDto(comicNode.get("thumbnail"));

        ComicDto dto = new ComicDto(
                comicNode.get("id").asLong(),
                comicNode.get("title").asText(),
                comicNode.get("description").asText(),
                comicNode.get("modified").asText(),
                comicNode.get("resourceURI").asText(),
                thumbnail
                );
        return dto;
    }

    private static ArrayNode getResultsNode(JsonNode response) {
        if (response == null) {
            throw new IllegalArgumentException("The node cannot be null");
        }
        JsonNode data = response.get("data");
        ArrayNode results = (ArrayNode) data.get("results");
        return results;
    }
}
