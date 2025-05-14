package com.api.apitest.services;

import java.util.Map;

public interface HttpClientService {

    <T> T doGet(String url, Map<String, String> queryParams, Class<T> responseType);
    <T,R> T doPost(String url, Map<String, String> queryParams, Class<T> responseType, R bodyRequest);
    <T,R> T doPut(String url, Map<String, String> queryParams, Class<T> responseType, R bodyRequest);
    <T> T dodelete(String url, Map<String, String> queryParams, Class<T> responseType);
}
