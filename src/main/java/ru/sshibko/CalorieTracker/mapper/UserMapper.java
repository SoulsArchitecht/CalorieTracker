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
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .age(userDto.getAge())
                .gender(Gender.valueOf(userDto.getGender()))
                .weight(userDto.getWeight())
                .height(userDto.getHeight())
                .goal(Goal.valueOf(userDto.getGoal()))
                .dailyCalories(userDto.getDailyCalories())
                .build();
    }


    //TODO if it'll need null init status for enums

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
