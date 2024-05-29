package com.jaoow.urlshortener.controller;

import com.jaoow.urlshortener.controller.dto.UrlDataResponse;
import com.jaoow.urlshortener.controller.dto.ShortenUrlRequest;
import com.jaoow.urlshortener.controller.dto.ShortenUrlResponse;
import com.jaoow.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> shortenURL(@RequestBody ShortenUrlRequest request, HttpServletRequest httpServletRequest) {
        var redirectUrl = urlService.getRedirectUrl(request, httpServletRequest);
        return ResponseEntity.ok(redirectUrl);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirectURL(@PathVariable String id) {
        var shortUrl = urlService.getShortUrl(id);
        if (shortUrl.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        urlService.incrementAccessCount(shortUrl.get());
        return ResponseEntity.status(HttpStatus.FOUND).header("Location", shortUrl.get().getOriginalURL()).build();
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<UrlDataResponse> getShortUrlData(@PathVariable String id) {
        var shortUrl = urlService.getShortUrlData(id);
        return shortUrl.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
