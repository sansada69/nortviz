package com.nortvis.demo.controller;

import com.nortvis.demo.dto.ApiResponse;
import com.nortvis.demo.dto.CreateUserDto;
import com.nortvis.demo.dto.UserDto;
import com.nortvis.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(new ApiResponse<UserDto>(userService.getUser(userId)));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<UserDto>> getUser(@RequestParam("username") String username) {
        return ResponseEntity.ok(new ApiResponse<UserDto>(userService.getUser(username)));
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<UserDto>> saveUser(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(new ApiResponse<UserDto>(userService.saveUser(createUserDto)));
    }
}
