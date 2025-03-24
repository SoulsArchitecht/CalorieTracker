package ru.sshibko.CalorieTracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sshibko.CalorieTracker.dto.DailyHistoryDto;
import ru.sshibko.CalorieTracker.dto.InDailyLimitDto;
import ru.sshibko.CalorieTracker.dto.ReportDto;
import ru.sshibko.CalorieTracker.exception.ResourceNotFoundException;
import ru.sshibko.CalorieTracker.model.Meal;
import ru.sshibko.CalorieTracker.model.User;
import ru.sshibko.CalorieTracker.repository.MealRepository;
import ru.sshibko.CalorieTracker.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final MealRepository mealRepository;

    private final UserRepository userRepository;

    public ReportDto getTotalCaloriesForDay(long userId, LocalDateTime date) {
        List<Meal> meals = mealRepository.findByUserIdAndDate(userId, date);
        int result = meals.stream()
                .mapToInt(meal -> meal.getTotalCalories() * meal.getQuantity()).sum();
        log.info("Total daily calories for user with id: {} is {}", userId, result);
        return ReportDto.builder()
                .userId(userId)
                .timestamp(date)
                .totalCaloriesForDay(result)
                .build();
    }

    public InDailyLimitDto isWithinDailyLimit(Long userId, LocalDateTime date) {
        double totalCaloriesForDay = getTotalCaloriesForDay(userId, date).getTotalCaloriesForDay();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userId));
        log.info("Check daily limit for user with id: {}", userId);
        return InDailyLimitDto.builder()
                .userId(userId)
                .timestamp(date)
                .inDailyLimit(totalCaloriesForDay <= user.getDailyCalories())
                .build();
    }

    public DailyHistoryDto getDailyHistory(Long userId) {
        List<Meal> meals = mealRepository.findAllByUserId(userId);
        return DailyHistoryDto.builder()
                .userId(userId)
                .meals(meals)
                .build();
    }
}
