package com.jaoow.urlshortener.repository;


import com.jaoow.urlshortener.entity.ShortUrl;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<ShortUrl, String> {
}
