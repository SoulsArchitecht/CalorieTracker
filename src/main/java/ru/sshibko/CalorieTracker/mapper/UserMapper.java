package ru.sshibko.CalorieTracker.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sshibko.CalorieTracker.dto.UserDto;
import ru.sshibko.CalorieTracker.model.User;
import ru.sshibko.CalorieTracker.model.enums.Gender;
import ru.sshibko.CalorieTracker.model.enums.Goal;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User mapToEntity(UserDto userDto) {
        Gender gender = null;
        if (userDto.getGender() != null) {
            gender = Gender.valueOf(userDto.getGender());
        } else {
            gender = Gender.MALE;
        }

        Goal goal = null;
        if (userDto.getGoal() != null) {
            goal = Goal.valueOf(userDto.getGoal());
        } else {
            goal = Goal.WEIGHT_MAINTENANCE;
        }

        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .age(userDto.getAge())
                .gender(gender)
                .weight(userDto.getWeight())
                .height(userDto.getHeight())
                .goal(goal)
                .dailyCalories(userDto.getDailyCalories())
                .build();
    }

    public UserDto mapToDto(User user) {
        return UserDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .age(user.getAge())
                .gender(String.valueOf(user.getGender()))
                .weight(user.getWeight())
                .height(user.getHeight())
                .goal(String.valueOf(user.getGoal()))
                .dailyCalories(user.getDailyCalories())
                .build();
    }
}
