package ru.sshibko.CalorieTracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a Meal in the app
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Представление приема пищи в приложении")
public class MealDto {

    @Schema(description = "ID пользователя принимающего пищу", example = "1")
    private Long userId;

    @Schema(description = "Список идентификаторов блюд", example = "[2, 5]")
    private List<Long> dishIds;

    @Schema(description = "Время приема пищи (вставляется автоматически)", example = "2025-03-24T23:30:10")
    private LocalDateTime timestamp;

    @Schema(description = "Количество повторений приема пищи (минимум 1)", example = "1")
    @Min(1)
    private int quantity;

    @Override
    public String toString() {
        return "MealEntryDto{" +
                "userId=" + userId +
                ", timestamp=" + timestamp +
                ", quantity=" + quantity +
                '}';
    }
}
