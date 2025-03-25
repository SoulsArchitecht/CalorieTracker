package ru.sshibko.CalorieTracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Represents a Dish in the app
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Представление блюд в приложении")
public class DishDto {

    @Schema(description = "Название блюда", example = "Борщ")
    @NotBlank
    private String name;

    @Schema(description = "Калорийность блюда целиком", example = "300")
    @Min(1)
    private int caloriesPerServing;

    @Schema(description = "Протеины", example = "1.2")
    private double proteins;

    @Schema(description = "Жиры", example = "3.2")
    private double fats;

    @Schema(description = "Углеводы", example = "0.2")
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
