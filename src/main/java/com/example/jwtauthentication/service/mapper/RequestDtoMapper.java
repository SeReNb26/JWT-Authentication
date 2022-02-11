package com.example.jwtauthentication.service.mapper;

public interface RequestDtoMapper<T, D> {
    T mapToModel(D dto);
}
