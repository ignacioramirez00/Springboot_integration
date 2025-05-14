package com.api.apitest.services.impl;

import java.util.Map;

import com.api.apitest.services.HttpClientService;

public class RestTemplateService implements HttpClientService {

    @Override
    public <T> T doGet(String url, Map<String, String> queryParams, Class<T> responseType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T, R> T doPost(String url, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T, R> T doPut(String url, Map<String, String> queryParams, Class<T> responseType, R bodyRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T dodelete(String url, Map<String, String> queryParams, Class<T> responseType) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
