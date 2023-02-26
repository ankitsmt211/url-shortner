package com.alphabee.shortner.controller;

import com.alphabee.shortner.record.Request;
import com.alphabee.shortner.service.URLService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
public class URLController {
    @Autowired
    private URLService urlService;

    // URL should have complete address ex: "https://www.google.com"
    @PostMapping("/generate")
    public ResponseEntity<String> generateURL(@RequestBody Request request) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return urlService.generateURL(request);
    }

    @GetMapping("/resolve/{shortURL}")
    public void resolveURL(HttpServletResponse httpServletResponse, @PathVariable String shortURL) throws IOException {
       urlService.fetchURL(httpServletResponse,shortURL);
    }

}
