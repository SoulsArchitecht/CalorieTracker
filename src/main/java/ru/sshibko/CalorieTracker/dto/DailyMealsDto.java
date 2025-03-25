package ru.sshibko.CalorieTracker.dto;

import lombok.*;
import ru.sshibko.CalorieTracker.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyMealsDto {
    private LocalDate date;
    private List<Meal> meals;
    private int totalCalories;
}
