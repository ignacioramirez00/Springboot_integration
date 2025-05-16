package com.api.apitest.persistence.integration;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.api.apitest.dto.MyPageable;

@Component
public class MarvelAPIConfig {

    @Autowired
    @Qualifier("md5Encoder")
    private PasswordEncoder md5Encoder;

    private Long timestamp = new Date(System.currentTimeMillis()).getTime();
    @Value("${integration.marvel.public-key}")
    private String publicKey;
    @Value("${integration.marvel.private-key}")
    private String privateKey;

    private String getHash() {
        String hasDecoded = Long.toString(timestamp).concat(publicKey).concat(privateKey);
        return md5Encoder.encode(hasDecoded);
    }

    public Map<String,String> getAuthenticationQueryParams() {
        Map<String,String> queryParams = new HashMap<>();
        queryParams.put("ts", Long.toString(timestamp));
        queryParams.put("apikey", publicKey);
        queryParams.put("hash", getHash());
        return queryParams;
    }

    public Map<String, String> getQueryParamsForFindAll(MyPageable myPageable, long characterId) {
        Map<String, String> queryParams = getAuthenticationQueryParams();
        queryParams.put("limit", String.valueOf(myPageable.limit()));
        queryParams.put("offset", String.valueOf(myPageable.offset()));
        if (characterId > 0) {
            queryParams.put("characterId", String.valueOf(characterId));
        }
        return queryParams;
    }
}
