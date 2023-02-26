package com.alphabee.shortner.service;

import com.alphabee.shortner.record.Request;
import java.io.IOException;
import java.net.URI;
import javax.servlet.http.HttpServletResponse;
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
        String encodedCleanURL = cleanURL(encodedURL);
        urlDirectory.put(encodedCleanURL, request.url());
        return  ResponseEntity.ok().body(encodedCleanURL);
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

    //base-64 included "/" in encoded values
    // which resulted in bad urls
    public String cleanURL(String shortURL){
        return shortURL.replaceAll("/","20");
    }


    public void fetchURL(HttpServletResponse httpServletResponse,String shortURL) throws IOException {
        for(String urlsKey : urlDirectory.keySet()){
            if(urlsKey.equals(shortURL)){
              httpServletResponse.sendRedirect(urlDirectory.get(shortURL));
            }
        }
    }

}
