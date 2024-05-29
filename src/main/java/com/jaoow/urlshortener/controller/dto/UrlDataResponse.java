package com.jaoow.urlshortener.controller.dto;

public record InfoResponse(String id, String originalUrl, int accessCount, long lastAccessed, long expiresAt) {
}
