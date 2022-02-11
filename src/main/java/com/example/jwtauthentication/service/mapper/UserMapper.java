package com.example.jwtauthentication.service.mapper;

import org.springframework.stereotype.Component;
import com.example.jwtauthentication.dto.request.UserRequestDto;
import com.example.jwtauthentication.dto.response.UserResponseDto;
import com.example.jwtauthentication.model.User;

@Component
public class UserMapper implements RequestDtoMapper<User, UserRequestDto>,
                                    ResponseDtoMapper<UserResponseDto, User> {

    @Override
    public User mapToModel(UserRequestDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    @Override
    public UserResponseDto mapToDto(User model) {
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(model.getId());
        responseDto.setName(model.getName());
        responseDto.setLogin(model.getLogin());
        responseDto.setRole(model.getRole());
        return responseDto;
    }
}
