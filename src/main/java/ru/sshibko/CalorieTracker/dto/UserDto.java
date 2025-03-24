package ru.sshibko.CalorieTracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String email;

    @Min(1) @Max(100)
    private int age;

    private String gender;

    @Min(1) @Max(300)
    private double weight;

    @Min(1) @Max(300)
    private double height;

    private String goal;

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
