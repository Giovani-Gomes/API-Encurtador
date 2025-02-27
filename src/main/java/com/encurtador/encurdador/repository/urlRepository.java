package com.encurtador.encurdador.repository;

import com.encurtador.encurdador.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface urlRepository extends JpaRepository<Url,Long> {
    Optional<Url>findByShortUrl(String shortUrl);
}
