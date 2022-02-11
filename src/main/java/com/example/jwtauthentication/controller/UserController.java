package com.example.jwtauthentication.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.jwtauthentication.dto.request.UserRequestDto;
import com.example.jwtauthentication.dto.response.UserResponseDto;
import com.example.jwtauthentication.model.User;
import com.example.jwtauthentication.service.UserService;
import com.example.jwtauthentication.service.mapper.RequestDtoMapper;
import com.example.jwtauthentication.service.mapper.ResponseDtoMapper;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ResponseDtoMapper<UserResponseDto, User> responseDtoMapper;
    private final RequestDtoMapper<User, UserRequestDto> requestDtoMapper;

    public UserController(UserService userService, ResponseDtoMapper<UserResponseDto, User> responseDtoMapper, RequestDtoMapper<User, UserRequestDto> requestDtoMapper) {
        this.userService = userService;
        this.responseDtoMapper = responseDtoMapper;
        this.requestDtoMapper = requestDtoMapper;
    }

    @GetMapping
    public List<UserResponseDto> getAll() {
        List<UserResponseDto> list = new ArrayList<>();
        for (User user : userService.getAll()) {
            list.add(responseDtoMapper.mapToDto(user));
        }
        return list;
    }

    @GetMapping("/{id}")
    public UserResponseDto getById(@PathVariable() Long id) {
        return responseDtoMapper.mapToDto(userService.getById(id));
    }

    @PostMapping
    public void createUser(@RequestBody UserRequestDto userRequestDto) {
        userService.add(requestDtoMapper.mapToModel(userRequestDto));
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        userService.update(id, userRequestDto.getName(), userRequestDto.getLogin(),
                userRequestDto.getPassword(), userRequestDto.getRole());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
