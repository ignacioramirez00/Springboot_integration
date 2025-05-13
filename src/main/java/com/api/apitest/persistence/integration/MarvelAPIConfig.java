package com.api.apitest.persistence.integration;
import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MarvelAPIConfig {

    @Autowired
    @Qualifier("md5Encoder")
    private PasswordEncoder md5Encoder;

    private Long timestamp = new Date(System.currentTimeMillis()).getTime();
    @Value("${integration.marvel.publicKey}")
    private String publicKey;
    @Value("${integration.marvel.privateKey}")
    private String privateKey;

    private String getHash() {
        String hasDecoded = Long.toString(timestamp).concat(publicKey).concat(privateKey);
        return md5Encoder.encode(hasDecoded);
    }

    public Map<String,String> getAuthenticationQueryParams() {
        Map<String,String> queryParams = new java.util.HashMap<>();
        queryParams.put("ts", Long.toString(timestamp));
        queryParams.put("apikey", publicKey);
        queryParams.put("hash", getHash());
        return queryParams;
    }
}
