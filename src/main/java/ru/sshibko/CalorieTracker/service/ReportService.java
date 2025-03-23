package ru.sshibko.CalorieTracker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sshibko.CalorieTracker.dto.InDailyLimitDto;
import ru.sshibko.CalorieTracker.dto.ReportDto;
import ru.sshibko.CalorieTracker.exception.ResourceNotFoundException;
import ru.sshibko.CalorieTracker.model.MealEntry;
import ru.sshibko.CalorieTracker.model.User;
import ru.sshibko.CalorieTracker.repository.MealEntryRepository;
import ru.sshibko.CalorieTracker.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final MealEntryRepository mealEntryRepository;

    private final UserRepository userRepository;

    public ReportDto getTotalCaloriesForDay(long userId, LocalDateTime date) {
        List<MealEntry> entries = mealEntryRepository.findByUserIdAndDate(userId, date);

        double result = entries.stream()
                .mapToDouble(entry -> entry.getMeal().getCaloriesPerServing() *
                        entry.getQuantity()).sum();

        return new ReportDto(result);
    }

    public InDailyLimitDto isWithinDailyLimit(Long userId, LocalDateTime date) {
        double totalCaloriesForDay = getTotalCaloriesForDay(userId, date).getTotalCaloriesForDay();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + userId));
        return new InDailyLimitDto(totalCaloriesForDay <= user.getDailyCalories());
    }
}
