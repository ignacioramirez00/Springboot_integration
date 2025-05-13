package com.api.apitest.persistence.integration.marvel.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.api.apitest.dto.MyPageable;
import com.api.apitest.persistence.integration.marvel.dto.ComicDto;

@Repository
public class ComicRepository {

    public List<ComicDto> findAll(MyPageable myPageable, long characterId) {
        return null;
    }

    public ComicDto findById(long comicId) {
        return null;
    }
}
