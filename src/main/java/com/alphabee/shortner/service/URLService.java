package com.alphabee.shortner.service;

import com.alphabee.shortner.record.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class URLService {
    private static Map<String,String> urlDirectory = new HashMap<>();
    private static int URL_LENGTH = 7;

    public ResponseEntity<String> generateURL(Request request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String encodedURL = encodeURL(request.url());
        urlDirectory.put(encodedURL, request.url());
       // System.out.println(encodedURL);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    public String encodeURL(String longUrl) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //convert to shorter URL
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte [] longUrlToByteArray = longUrl.getBytes(StandardCharsets.UTF_8);
        byte [] md5Hash = messageDigest.digest(longUrlToByteArray);

        Base64.Encoder encoder = Base64.getEncoder();

        String encodeToString = encoder.encodeToString(md5Hash);

        return encodeToString.substring(0,URL_LENGTH);
    }

}
