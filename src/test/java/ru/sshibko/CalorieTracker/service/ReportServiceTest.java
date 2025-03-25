package ru.sshibko.CalorieTracker.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sshibko.CalorieTracker.dto.DailyHistoryDto;
import ru.sshibko.CalorieTracker.dto.DailyMealsDto;
import ru.sshibko.CalorieTracker.dto.InDailyLimitDto;
import ru.sshibko.CalorieTracker.dto.ReportDto;
import ru.sshibko.CalorieTracker.exception.ResourceNotFoundException;
import ru.sshibko.CalorieTracker.model.Dish;
import ru.sshibko.CalorieTracker.model.Meal;
import ru.sshibko.CalorieTracker.model.User;
import ru.sshibko.CalorieTracker.model.enums.Gender;
import ru.sshibko.CalorieTracker.model.enums.Goal;
import ru.sshibko.CalorieTracker.repository.MealRepository;
import ru.sshibko.CalorieTracker.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {
    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ReportService reportService;

    private User createTestUser() {
        return User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .age(30)
                .gender(Gender.MALE)
                .weight(70.0)
                .height(175.0)
                .goal(Goal.WEIGHT_MAINTENANCE)
                .dailyCalories(2000)
                .build();
    }

    private Dish createDish(Long id, String name, int calories) {
        return Dish.builder()
                .id(id)
                .name(name)
                .caloriesPerServing(calories)
                .proteins(0)
                .fats(0)
                .carbohydrates(0)
                .build();
    }

    private Meal createMeal(Long id, User user, List<Dish> dishes, LocalDateTime timestamp, int quantity) {
        return Meal.builder()
                .id(id)
                .user(user)
                .dishes(dishes)
                .timestamp(timestamp)
                .quantity(quantity)
                .build();
    }

    @Test
    @DisplayName("Check that getTotalCaloriesForDay should return correct report")
    void getTotalCaloriesForDayShouldReturnCorrectReport() {
        User user = createTestUser();
        LocalDateTime date = LocalDateTime.now();

        Dish dish1 = createDish(1L, "soup", 300);
        Dish dish2 = createDish(2L, "porridge", 500);

        Meal meal1 = createMeal(1L, user, List.of(dish1), date, 2);
        Meal meal2 = createMeal(2L, user, List.of(dish2), date, 1);

        List<Meal> meals = List.of(meal1, meal2);

        when(mealRepository.findByUserIdAndDate(user.getId(), date)).thenReturn(meals);

        int expectedTotalCalories = 1100;

        ReportDto result = reportService.getTotalCaloriesForDay(user.getId(), date);

        assertNotNull(result);
        assertEquals(user.getId(), result.getUserId());
        assertEquals(date, result.getTimestamp());
        assertEquals(expectedTotalCalories, result.getTotalCaloriesForDay());

        verify(mealRepository).findByUserIdAndDate(user.getId(), date);
    }

    @Test
    @DisplayName("Check that isWithinDailyLimit() should return true")
    void isWithinDailyLimitWhenWithinLimitShouldReturnTrue() {
        User user = createTestUser();
        LocalDateTime date = LocalDateTime.now();

        Dish dish1 = createDish(1L, "soup", 300);
        Dish dish2 = createDish(2L, "porridge", 500);

        Meal meal1 = createMeal(1L, user, List.of(dish1), date, 2);
        Meal meal2 = createMeal(2L, user, List.of(dish2), date, 1);

        List<Meal> meals = List.of(meal1, meal2);

        when(mealRepository.findByUserIdAndDate(user.getId(), date)).thenReturn(meals);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        InDailyLimitDto result = reportService.isWithinDailyLimit(user.getId(), date);

        assertNotNull(result);
        assertEquals(user.getId(), result.getUserId());
        assertEquals(date, result.getTimestamp());
        assertTrue(result.isInDailyLimit());

        verify(mealRepository).findByUserIdAndDate(user.getId(), date);
        verify(userRepository).findById(user.getId());
    }

    @Test
    @DisplayName("Check that isWithinDailyLimit() should return false")
    void isWithinDailyLimitWhenExceedsLimitShouldReturnFalse() {
        User user = createTestUser();
        user.setDailyCalories(1000);
        LocalDateTime date = LocalDateTime.now();

        Dish dish1 = createDish(1L, "soup", 300);
        Dish dish2 = createDish(2L, "porridge", 500);

        Meal meal1 = createMeal(1L, user, List.of(dish1), date, 2);
        Meal meal2 = createMeal(2L, user, List.of(dish2), date, 1);

        List<Meal> meals = List.of(meal1, meal2);

        when(mealRepository.findByUserIdAndDate(user.getId(), date)).thenReturn(meals);
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        InDailyLimitDto result = reportService.isWithinDailyLimit(user.getId(), date);

        assertNotNull(result);
        assertEquals(user.getId(), result.getUserId());
        assertEquals(date, result.getTimestamp());
        assertFalse(result.isInDailyLimit());
    }

    @Test
    @DisplayName("Check that isWithinDailyLimit() should throw exception when user not found")
    void isWithinDailyLimitWhenUserNotFoundShouldThrowException() {
        long userId = 5L;
        LocalDateTime date = LocalDateTime.now();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> reportService.isWithinDailyLimit(userId, date));
    }

    @Test
    @DisplayName("Check getDailyHistory() return empty list when no meals")
    void getDailyHistoryWhenNoMealsShouldReturnEmptyList() {
        Long userId = 1L;
        when(mealRepository.findAllByUserIdGroupingDate(userId)).thenReturn(Collections.emptyList());

        DailyHistoryDto result = reportService.getDailyHistoryForDay(userId);

        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertTrue(result.getDailyMeals().isEmpty());
        verify(mealRepository).findAllByUserIdGroupingDate(userId);
    }

    @Test
    @DisplayName("Check getDailyHistory() return correct grouping and sorting when days are many")
    void getDailyHistoryWithMultipleDaysMealsShouldReturnCorrectGroupingAndSorting() {
        User user = createTestUser();
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);

        Dish dish1 = createDish(1L, "soup", 300);
        Dish dish2 = createDish(2L, "porridge", 500);
        Dish dish3 = createDish(3L, "dessert", 700);

        Meal meal1 = createMeal(1L, user, List.of(dish1), today, 2);
        Meal meal2 = createMeal(2L, user, List.of(dish2), today.plusHours(3), 1);
        Meal meal3 = createMeal(3L, user, List.of(dish3), yesterday, 1);

        when(mealRepository.findAllByUserIdGroupingDate(user.getId()))
                .thenReturn(List.of(meal1, meal2, meal3));

        DailyHistoryDto result = reportService.getDailyHistoryForDay(user.getId());

        assertNotNull(result);
        assertEquals(user.getId(), result.getUserId());
        assertEquals(2, result.getDailyMeals().size());

        assertEquals(today.toLocalDate(), result.getDailyMeals().get(0).getDate());
        assertEquals(yesterday.toLocalDate(), result.getDailyMeals().get(1).getDate());

        DailyMealsDto todayMeals = result.getDailyMeals().get(0);
        assertEquals(2, todayMeals.getMeals().size());
        assertEquals(1100, todayMeals.getTotalCalories());

        DailyMealsDto yesterdayMeals = result.getDailyMeals().get(1);
        assertEquals(1, yesterdayMeals.getMeals().size());
        assertEquals(700, yesterdayMeals.getTotalCalories());

        verify(mealRepository).findAllByUserIdGroupingDate(user.getId());
    }

    @Test
    @DisplayName("Check that getDailyHistory() calculate total calories correctly")
    void getDailyHistoryShouldCalculateTotalCaloriesCorrectly() {
        User user = createTestUser();
        LocalDateTime date = LocalDateTime.now();

        Dish dish1 = createDish(1L, "soup", 300);
        Dish dish2 = createDish(2L, "porridge", 500);

        Meal meal1 = createMeal(1L, user, List.of(dish1, dish2), date, 2);
        Meal meal2 = createMeal(2L, user, List.of(dish2), date.plusHours(3), 3);

        when(mealRepository.findAllByUserIdGroupingDate(user.getId()))
                .thenReturn(List.of(meal1, meal2));

        DailyHistoryDto result = reportService.getDailyHistoryForDay(user.getId());

        DailyMealsDto dailyMeals = result.getDailyMeals().get(0);
        assertEquals(3100, dailyMeals.getTotalCalories());
    }
}