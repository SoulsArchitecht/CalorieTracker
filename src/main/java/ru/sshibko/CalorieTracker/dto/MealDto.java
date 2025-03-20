package ru.sshibko.CalorieTracker.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealDto {

    private String name;
    private double caloriesPerServing;
    private double proteins;
    private double fats;
    private double carbohydrates;
}
