package com.api.apitest.web.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.apitest.dto.MyPageable;
import com.api.apitest.persistence.integration.marvel.dto.ComicDto;
import com.api.apitest.services.ComicService;

@RestController
@RequestMapping("/comics")
public class ComicController {

    // voy a hacer el punto numero 2) donde pide un listado de comics que tiene
    // un personaje especifico
    @Autowired
    private ComicService comicService;

    @GetMapping
    public ResponseEntity<List<ComicDto>> findComicsByCharacterId(
            @RequestParam(required = false) long characterId,
            @RequestParam long offset,
            @RequestParam long limit) {
        
        MyPageable pageable = new MyPageable(offset, limit);
        return ResponseEntity.ok(comicService.findAll(pageable,characterId));
    }

    // punto numero 5
    @GetMapping("/{comicId}")
    public ResponseEntity<ComicDto> findById(@PathVariable long comicId) {
        return ResponseEntity.ok(comicService.findById(comicId));
    }

}
