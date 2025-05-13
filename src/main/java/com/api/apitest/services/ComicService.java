package com.api.apitest.services;

import java.util.List;

import com.api.apitest.dto.MyPageable;
import com.api.apitest.persistence.integration.marvel.dto.ComicDto;

public interface ComicService {

    List<ComicDto> findAll(MyPageable myPageable, long characterId);
    ComicDto findById(long comicId);
}
    
