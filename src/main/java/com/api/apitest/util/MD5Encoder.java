package com.api.apitest.util;

import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;

@Component("md5Encoder")
public class MD5Encoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(rawPassword.toString().getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //esto se hace para hacer una evaluacion de lo codificado sea igual a lo de sin codificar
        return encode(rawPassword).equals(encodedPassword);
    }

}
