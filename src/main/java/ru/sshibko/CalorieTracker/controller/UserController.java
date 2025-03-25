package ru.sshibko.CalorieTracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sshibko.CalorieTracker.dto.UserDto;
import ru.sshibko.CalorieTracker.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "Операции с пользователями/админка")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Получить пользователя по ID")
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @Operation(summary = "Получить список всех пользователей")
    @GetMapping
    public Collection<UserDto> getAllUsers() {
        return userService.getAll();
    }

    @Operation(summary = "Создать нового пользователя")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.create(userDto);
    }

    @Operation(summary = "Изменить существующего пользователя")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        return userService.update(id, userDto);
    }

    @Operation(summary = "Удалить существующего пользователя")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
    }
}
