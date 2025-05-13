package com.api.apitest.services.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.apitest.dto.MyPageable;
import com.api.apitest.persistence.integration.marvel.dto.CharacterDto;
import com.api.apitest.persistence.integration.marvel.dto.CharacterDto.CharacterInfoDto;
import com.api.apitest.persistence.integration.marvel.repository.CharacterRepository;
import com.api.apitest.services.CharacterService;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<CharacterDto> findAll(MyPageable myPageable, String name, int[] comics, int[] series) {
        List<CharacterDto>  characters =  characterRepository.findAll(myPageable, name, comics, series);
        return characters; 
    }

    @Override
    public CharacterInfoDto findById(long characterId) {
        return characterRepository.findById(characterId);
    }

}
