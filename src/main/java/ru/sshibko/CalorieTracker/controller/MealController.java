package ru.sshibko.CalorieTracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sshibko.CalorieTracker.dto.MealDto;
import ru.sshibko.CalorieTracker.service.MealService;

import java.util.Collection;

@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
@Tag(name = "Meal Controller", description = "Операции с приемами пищи")
public class MealController {

    private final MealService mealService;

    @Operation(summary = "Получить прием пищи по ID")
    @GetMapping("/{id}")
    public MealDto getMeal(@PathVariable("id") Long id) {
        return mealService.getById(id);
    }

    @Operation(summary = "Получить список всех приемов пищи")
    @GetMapping()
    public Collection<MealDto> getAllMeals() {
        return mealService.getAll();
    }

    @Operation(summary = "Добавить новый прием пищи")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealDto createMeal(@RequestBody MealDto mealDto) {
        return mealService.create(mealDto);
    }

    @Operation(summary = "Изменить существующий прем пищи")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMeal(@PathVariable("id") Long id, @RequestBody MealDto mealDto) {
        mealService.update(id, mealDto);
    }

    @Operation(summary = "Удалить существующий прием пищи")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMeal(@PathVariable("id") Long id) {
        mealService.delete(id);
    }
}
