package ru.sshibko.CalorieTracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sshibko.CalorieTracker.dto.DishDto;
import ru.sshibko.CalorieTracker.service.DishService;

import java.util.Collection;

@RestController
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @GetMapping("/{id}")
    public DishDto getDish(@PathVariable("id") Long id) {
        return dishService.getById(id);
    }

    @GetMapping()
    public Collection<DishDto> getAllDishes() {
        return dishService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DishDto createDish(@RequestBody DishDto dishDto) {
        return dishService.create(dishDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDish(@PathVariable("id") Long id, @RequestBody DishDto dishDto) {
        dishService.update(id, dishDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDish(@PathVariable("id") Long id) {
        dishService.delete(id);
    }
}
