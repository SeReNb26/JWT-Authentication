package com.example.jwtauthentication.service.mapper;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T model);
}
