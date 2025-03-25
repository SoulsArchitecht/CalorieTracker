package ru.sshibko.CalorieTracker.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sshibko.CalorieTracker.model.User;
import ru.sshibko.CalorieTracker.model.enums.Gender;
import ru.sshibko.CalorieTracker.model.enums.Goal;
import ru.sshibko.CalorieTracker.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("calculate Daily Calories For Female")
    void calculateDailyCaloriesShouldReturnCorrectValueIfFemale() {
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@test.com");
        user.setAge(30);
        user.setGender(Gender.FEMALE);
        user.setWeight(100);
        user.setHeight(170);
        user.setGoal(Goal.WEIGHT_LOSS);

        assertEquals(1401, (int)userService.calculateDailyCalories(user));

        user.setGoal(Goal.WEIGHT_MAINTENANCE);
        assertEquals(1751, (int)userService.calculateDailyCalories(user));

        user.setGoal(Goal.WEIGHT_GAIN);
        assertEquals(2101, (int)userService.calculateDailyCalories(user));
    }

    @Test
    @DisplayName("calculate Daily Calories For Male")
    void calculateDailyCaloriesShouldReturnCorrectValueIfMale() {
        User user = new User();
        user.setUsername("test");
        user.setEmail("test@test.com");
        user.setAge(50);
        user.setGender(Gender.MALE);
        user.setWeight(80);
        user.setHeight(185);
        user.setGoal(Goal.WEIGHT_LOSS);

        assertEquals(1369, (int)userService.calculateDailyCalories(user));

        user.setGoal(Goal.WEIGHT_MAINTENANCE);
        assertEquals(1711, (int)userService.calculateDailyCalories(user));

        user.setGoal(Goal.WEIGHT_GAIN);
        assertEquals(2053, (int)userService.calculateDailyCalories(user));
    }
}