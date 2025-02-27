package com.encurtador.encurdador.services;

import com.encurtador.encurdador.model.Url;
import com.encurtador.encurdador.repository.urlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {

    @Autowired
    private urlRepository urlRepository;

    public String shortenUrl(String originalUrl){

        String shortUrl = genetareShortUrl();
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortUrl);
        url.setExpitationDate(LocalDateTime.now().plusDays(30));
        urlRepository.save(url);
        return shortUrl;
    }

    public Optional<Url> getOriginalUrl(String shortUrl){
        Optional<Url> urlOptional = urlRepository.findByShortUrl(shortUrl);
        if (urlOptional.isPresent()){
            Url url = urlOptional.get();
            if (url.getExpitationDate().isAfter(LocalDateTime.now())){
                return Optional.of(url);
            }
            else {
                urlRepository.delete(url);
            }
        }
        return Optional.empty();
    }

    private String genetareShortUrl(){
        String characteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();
        int lenght = 5 + random.nextInt(6);
        for (int i = 0; i < lenght; i++) {
            shortUrl.append(characteres.charAt(random.nextInt(characteres.length())));
        }
        return shortUrl.toString();
    }


}
