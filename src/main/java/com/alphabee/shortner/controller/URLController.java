package com.alphabee.shortner.controller;

import com.alphabee.shortner.record.Request;
import com.alphabee.shortner.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
public class URLController {
    @Autowired
    private URLService urlService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateURL(@RequestBody Request request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return urlService.generateURL(request);
    }
}
