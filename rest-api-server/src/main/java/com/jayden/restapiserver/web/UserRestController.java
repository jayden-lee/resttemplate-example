package com.jayden.restapiserver.web;

import com.jayden.restapiserver.domain.UserService;
import com.jayden.restapiserver.dto.UserResponseDto;
import com.jayden.restapiserver.dto.UserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController(value = "/api/v1")
public class UserRestController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/users")
    public Long save(@RequestBody UserSaveRequestDto requestDto) {
        return userService.save(requestDto);
    }
}
