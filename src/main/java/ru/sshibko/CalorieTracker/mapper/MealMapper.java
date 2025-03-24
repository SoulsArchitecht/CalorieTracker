package ru.sshibko.CalorieTracker.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sshibko.CalorieTracker.dto.MealDto;
import ru.sshibko.CalorieTracker.exception.ResourceNotFoundException;
import ru.sshibko.CalorieTracker.model.Dish;
import ru.sshibko.CalorieTracker.model.Meal;
import ru.sshibko.CalorieTracker.model.User;
import ru.sshibko.CalorieTracker.repository.DishRepository;
import ru.sshibko.CalorieTracker.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MealMapper {

    private final UserRepository userRepository;
    private final DishRepository dishRepository;

    public Meal mapToEntity(MealDto mealDto) {
        User user = userRepository.findById(mealDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id "
                + mealDto.getUserId()));
        List<Dish> dishes = dishRepository.findAllById(mealDto.getDishIds());
        if (dishes.isEmpty()) {
            throw new ResourceNotFoundException("Dishes not found");
        }

        return Meal.builder()
                .user(user)
                .dishes(dishes)
                .timestamp(mealDto.getTimestamp())
                .quantity(mealDto.getQuantity())
                .build();
    }

    public MealDto mapToDto(Meal meal) {
        return MealDto.builder()
                .userId(meal.getUser().getId())
                .dishIds(meal.getDishes().stream().map(Dish::getId).collect(Collectors.toList()))
                .timestamp(meal.getTimestamp())
                .quantity(meal.getQuantity())
                .build();
    }
}
