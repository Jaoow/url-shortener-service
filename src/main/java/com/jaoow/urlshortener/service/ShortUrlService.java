package com.jaoow.urlshortener.service;

import com.jaoow.urlshortener.controller.dto.UrlDataResponse;
import com.jaoow.urlshortener.controller.dto.ShortenUrlRequest;
import com.jaoow.urlshortener.controller.dto.ShortenUrlResponse;
import com.jaoow.urlshortener.entity.ShortUrl;
import com.jaoow.urlshortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    public ShortenUrlResponse getRedirectUrl(ShortenUrlRequest request, HttpServletRequest httpServletRequest) {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        Instant expiresAt = Instant.now().plus(7, ChronoUnit.DAYS);
        var shortUrl = ShortUrl.builder()
                .id(id)
                .originalURL(request.url())
                .accessCount(0)
                .expiresAt(expiresAt)
                .build();

        var redirectUrl = httpServletRequest.getRequestURL().toString().replace("shorten", id);
        urlRepository.save(shortUrl);
        return new ShortenUrlResponse(id, redirectUrl, expiresAt.toEpochMilli());
    }

    public Optional<ShortUrl> getShortUrl(String id) {
        return urlRepository.findById(id);
    }

    public Optional<UrlDataResponse> getShortUrlData(String id) {
        return urlRepository.findById(id)
                .map(url -> new UrlDataResponse(url.getId(), url.getOriginalURL(), url.getAccessCount(), url.getLastAccessed().toEpochMilli(), url.getExpiresAt().toEpochMilli()));
    }

    public void incrementAccessCount(ShortUrl shortUrl) {
        shortUrl.setLastAccessed(Instant.now());
        shortUrl.setAccessCount(shortUrl.getAccessCount() + 1);
        urlRepository.save(shortUrl);
    }
}
