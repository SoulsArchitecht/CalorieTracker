package ru.sshibko.CalorieTracker.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sshibko.CalorieTracker.dto.MealEntryDto;
import ru.sshibko.CalorieTracker.exception.ResourceNotFoundException;
import ru.sshibko.CalorieTracker.model.Meal;
import ru.sshibko.CalorieTracker.model.MealEntry;
import ru.sshibko.CalorieTracker.model.User;
import ru.sshibko.CalorieTracker.repository.MealRepository;
import ru.sshibko.CalorieTracker.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class MealEntryMapper {

    private final UserRepository userRepository;
    private final MealRepository mealRepository;

    public MealEntry mapToEntity(MealEntryDto mealEntryDto) {
        User user = userRepository.findById(mealEntryDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id "
                + mealEntryDto.getUserId()));
        Meal meal = mealRepository.findById(mealEntryDto.getMealId())
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id "
                + mealEntryDto.getMealId()));

        return MealEntry.builder()
                .user(user)
                .meal(meal)
                .timestamp(mealEntryDto.getTimestamp())
                .quantity(mealEntryDto.getQuantity())
                .build();
    }

    public MealEntryDto mapToDto(MealEntry mealEntry) {
        return MealEntryDto.builder()
                .userId(mealEntry.getUser().getId())
                .mealId(mealEntry.getMeal().getId())
                .timestamp(mealEntry.getTimestamp())
                .quantity(mealEntry.getQuantity())
                .build();
    }


}
