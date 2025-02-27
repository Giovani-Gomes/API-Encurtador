package com.encurtador.encurdador.controller;

import com.encurtador.encurdador.model.Url;
import com.encurtador.encurdador.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<Map<String,String>> shotenUrl(@RequestBody Map<String, String> request){


    String orinalUrl = request.get("url");
    String shortUrl = urlService.shortenUrl(orinalUrl);
    Map<String,String> reponse = new HashMap<String, String>();
    reponse.put("url","https://xxx.com/" + shortUrl);

        return ResponseEntity.ok(reponse);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Object> redirectOriginal(@PathVariable String shortUrl){

        Optional<Url> urlOptional = urlService.getOriginalUrl(shortUrl);
        if (urlOptional.isPresent()){
            Url url = urlOptional.get();
            System.out.println("Redirecionando para: "+url.getOriginalUrl());
            return ResponseEntity.status(302).location(URI.create(url.getOriginalUrl())).build();
        }
        System.out.println("URL não Encontrada ou expirada: " +shortUrl);

        return ResponseEntity.notFound().build();
    }
}
