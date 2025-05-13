package com.api.apitest.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.apitest.dto.MyPageable;
import com.api.apitest.persistence.integration.marvel.dto.ComicDto;
import com.api.apitest.persistence.integration.marvel.repository.ComicRepository;
import com.api.apitest.services.ComicService;

@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    private ComicRepository comicRepository;

    @Override
    public List<ComicDto> findAll(MyPageable myPageable, long characterId) {    
        return comicRepository.findAll(myPageable, characterId);
    }

    @Override
    public ComicDto findById(long comicId) {
        return comicRepository.findById(comicId);
    }

}
