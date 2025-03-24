package ru.sshibko.CalorieTracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishDto {

    @NotBlank
    private String name;

    @Min(1)
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
