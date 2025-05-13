package com.api.apitest.web.controller;

import com.api.apitest.dto.MyPageable;
import com.api.apitest.persistence.integration.marvel.dto.CharacterDto;
import com.api.apitest.services.CharacterService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class CharacterController {

        @Autowired
        private CharacterService characterService;


        //punto 1) donde se debe buscar 
        @GetMapping
        public ResponseEntity<List<CharacterDto>> findAll(
                        @RequestParam(required = false) String name,
                        @RequestParam(required = false) int[] comics,
                        @RequestParam(required = false) int[] series,
                        @RequestParam(required = false) long offset,
                        @RequestParam(required = false) long limit)

        {
                MyPageable myPageable = new MyPageable(offset, limit);
                return ResponseEntity.ok(characterService.findAll(myPageable, name, comics, series));
        }


        // punto 3) 
        @GetMapping("/{characterId}")
        public ResponseEntity<CharacterDto.CharacterInfoDto> findById(@PathVariable long characterId) {
                return ResponseEntity.ok(characterService.findById(characterId));
        }

}
