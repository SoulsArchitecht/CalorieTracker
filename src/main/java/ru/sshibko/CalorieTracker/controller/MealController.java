package ru.sshibko.CalorieTracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sshibko.CalorieTracker.dto.MealDto;
import ru.sshibko.CalorieTracker.service.MealService;

import java.util.Collection;

@RestController
@RequestMapping("/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/{id}")
    public MealDto getMeal(@PathVariable("id") Long id) {
        return mealService.getById(id);
    }

    @GetMapping()
    public Collection<MealDto> getAllMeals() {
        return mealService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealDto createMeal(@RequestBody MealDto mealDto) {
        return mealService.create(mealDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMeal(@PathVariable("id") Long id, @RequestBody MealDto mealDto) {
        mealService.update(id, mealDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMeal(@PathVariable("id") Long id) {
        mealService.delete(id);
    }

}
