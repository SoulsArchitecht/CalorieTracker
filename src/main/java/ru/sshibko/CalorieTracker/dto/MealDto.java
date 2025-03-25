package ru.sshibko.CalorieTracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealDto {

    private Long userId;

    private List<Long> dishIds;

    private LocalDateTime timestamp;

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
