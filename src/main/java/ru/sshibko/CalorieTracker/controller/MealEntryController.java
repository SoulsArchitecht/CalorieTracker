package ru.sshibko.CalorieTracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.sshibko.CalorieTracker.dto.MealEntryDto;
import ru.sshibko.CalorieTracker.service.MealEntryService;

import java.util.Collection;

@RestController
@RequestMapping("/mealEntries")
@RequiredArgsConstructor
public class MealEntryController {

    private final MealEntryService mealEntryService;

    @GetMapping("/{id}")
    public MealEntryDto getMeal(@PathVariable("id") Long id) {
        return mealEntryService.getById(id);
    }

    @GetMapping()
    public Collection<MealEntryDto> getAllMealEntries() {
        return mealEntryService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealEntryDto createMealEntry(@RequestBody MealEntryDto mealEntryDto) {
        return mealEntryService.create(mealEntryDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMealEntry(@PathVariable("id") Long id, @RequestBody MealEntryDto mealEntryDto) {
        mealEntryService.update(id, mealEntryDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMealEntry(@PathVariable("id") Long id) {
        mealEntryService.delete(id);
    }
}
