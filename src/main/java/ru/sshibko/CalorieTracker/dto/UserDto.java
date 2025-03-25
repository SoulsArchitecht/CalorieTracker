package ru.sshibko.CalorieTracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Represents a User in the app
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Представление пользователя приложения")
public class UserDto {

    @Schema(description = "Имя пользователя", example = "Иван Петров")
    @NotBlank
    private String username;

    @Schema(description = "Почтовый ящик пользователя, уникальный", example = "user@gmail.com")
    @NotBlank
    @Email
    private String email;

    @Schema(description = "Возраст пользователя в годах (от 1 до 105 лет)", example = "25")
    @Min(1) @Max(105)
    private int age;

    @Schema(description = "Пол пользователя", example = "мужской")
    private String gender;

    @Schema(description = "Вес пользователя в килограммах (от 1 до 300)", example = "90")
    @Min(1) @Max(300)
    private double weight;

    @Schema(description = "Рост пользователя в сантиметрах (от 1 до 300)", example = "169")
    @Min(1) @Max(300)
    private double height;

    @Schema(description = "Цель диеты (WEIGHT_LOSS, WEIGHT_MAINTENANCE, WEIGHT_GAIN)", example = "WEIGHT_LOSS")
    private String goal;

    @Schema(description = "Рассчитаная на пользователя дневная норма калорий")
    private double dailyCalories;

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", goal='" + goal + '\'' +
                ", dailyCalories=" + dailyCalories +
                '}';
    }
}
