package ru.sshibko.CalorieTracker.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sshibko.CalorieTracker.dto.MealDto;
import ru.sshibko.CalorieTracker.exception.ResourceNotFoundException;
import ru.sshibko.CalorieTracker.mapper.MealMapper;
import ru.sshibko.CalorieTracker.model.Dish;
import ru.sshibko.CalorieTracker.model.Meal;
import ru.sshibko.CalorieTracker.model.User;
import ru.sshibko.CalorieTracker.repository.MealRepository;
import ru.sshibko.CalorieTracker.repository.DishRepository;
import ru.sshibko.CalorieTracker.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MealService implements CRUDService<MealDto> {

    private final MealRepository mealRepository;

    private final MealMapper mealMapper;

    private final DishRepository dishRepository;

    private final UserRepository userRepository;

    @Override
    public MealDto getById(Long id) {
        log.info("Getting mealEntry with id: " + id);
        Meal meal = mealRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MealEntry not found with id: " + id));
        return mealMapper.mapToDto(meal);
    }

    @Override
    public Collection<MealDto> getAll() {
        log.info("Getting all mealEntries");
        List<Meal> mealsEntries = mealRepository.findAll();
        return mealsEntries.stream().map(mealMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MealDto create(MealDto mealDto) {
        Meal meal = mealMapper.mapToEntity(mealDto);
        Meal savedMeal = mealRepository.save(meal);
        log.info("Saving mealEntry with id: {}", savedMeal.getId());
        return mealMapper.mapToDto(savedMeal);
    }

    @Override
    @Transactional
    public MealDto update(Long id, MealDto mealDto) {
        Meal meal = mealRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MealEntry not found with id: " + id));
        List<Dish> dishes = dishRepository.findAllById(mealDto.getDishIds());
        User user = userRepository.findById(mealDto.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + id));

        meal.setDishes(dishes);
        meal.setUser(user);
        meal.setTimestamp(mealDto.getTimestamp());
        meal.setQuantity(mealDto.getQuantity());

        Meal updatedMeal = mealRepository.save(meal);
        log.info("Updating mealEntry with id: {}", updatedMeal.getId());
        return mealMapper.mapToDto(updatedMeal);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Meal meal = mealRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MealEntry not found with id: " + id));
        mealRepository.delete(meal);
        log.info("MealEntry with id: {} deleted successfully", id);
    }
}
