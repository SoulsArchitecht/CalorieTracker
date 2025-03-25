package ru.sshibko.CalorieTracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sshibko.CalorieTracker.dto.DishDto;
import ru.sshibko.CalorieTracker.service.DishService;

import java.util.Collection;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
@Tag(name = "Dish Controller", description = "Операции с блюдами")
public class DishController {

    private final DishService dishService;

    @Operation(summary = "Получить блюдо по ID")
    @GetMapping("/{id}")
    public DishDto getDish(@PathVariable("id") Long id) {
        return dishService.getById(id);
    }

    @Operation(summary = "Получить список всех блюд")
    @GetMapping()
    public Collection<DishDto> getAllDishes() {
        return dishService.getAll();
    }

    @Operation(summary = "Создать новое блюдо")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DishDto createDish(@RequestBody DishDto dishDto) {
        return dishService.create(dishDto);
    }

    @Operation(summary = "Изменить существующее блюдо")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDish(@PathVariable("id") Long id, @RequestBody DishDto dishDto) {
        dishService.update(id, dishDto);
    }

    @Operation(summary = "Удалить существующее блюдо")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDish(@PathVariable("id") Long id) {
        dishService.delete(id);
    }
}
