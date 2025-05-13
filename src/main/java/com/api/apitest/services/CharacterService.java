package com.api.apitest.services;

import java.util.List;
import com.api.apitest.dto.MyPageable;
import com.api.apitest.persistence.integration.marvel.dto.CharacterDto;

public interface CharacterService {

    List<CharacterDto> findAll(MyPageable myPageable, String name, int[] comics, int[] series);
    CharacterDto.CharacterInfoDto findById(long characterId);

}
