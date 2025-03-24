package ru.sshibko.CalorieTracker.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishDto {

    private String name;
    private int caloriesPerServing;
    private double proteins;
    private double fats;
    private double carbohydrates;

    @Override
    public String toString() {
        return "MealDto{" +
                "name='" + name + '\'' +
                ", caloriesPerServing=" + caloriesPerServing +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}
