package ru.sshibko.CalorieTracker.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sshibko.CalorieTracker.dto.MealDto;
import ru.sshibko.CalorieTracker.exception.ResourceNotFoundException;
import ru.sshibko.CalorieTracker.mapper.MealMapper;
import ru.sshibko.CalorieTracker.model.Meal;
import ru.sshibko.CalorieTracker.model.User;
import ru.sshibko.CalorieTracker.repository.MealRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MealService implements CRUDService<MealDto> {

    private final MealRepository mealRepository;

    private final MealMapper mealMapper;

    @Override
    public MealDto getById(Long id) {
        log.info("Getting meal with id: " + id);
        Meal meal = mealRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal not found with id: " + id));
        return mealMapper.mapToDto(meal);
    }

    @Override
    public Collection<MealDto> getAll() {
        log.info("Getting all meals");
        List<Meal> meals = mealRepository.findAll();
        return meals.stream().map(mealMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MealDto create(@Valid MealDto mealDto) {
        Meal meal = mealMapper.mapToEntity(mealDto);
        Meal savedMeal = mealRepository.save(meal);
        log.info("Saving meal with id: {}", savedMeal.getId());
        return mealMapper.mapToDto(savedMeal);
    }

    @Override
    @Transactional
    public MealDto update(@Valid Long id, @Valid MealDto mealDto) {
        Meal meal = mealRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal not found with id: " + id));
        meal.setName(mealDto.getName());
        meal.setProteins(mealDto.getProteins());
        meal.setFats(mealDto.getFats());
        meal.setCarbohydrates(mealDto.getCarbohydrates());
        meal.setCaloriesPerServing(mealDto.getCaloriesPerServing());

        Meal updatedMeal = mealRepository.save(meal);
        log.info("Updating meal with id: {}", updatedMeal.getId());
        return mealMapper.mapToDto(updatedMeal);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Meal meal = mealRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Meal not found with id: " + id));
        mealRepository.delete(meal);
        log.info("User with id: {} deleted successfully", id);
    }
}
