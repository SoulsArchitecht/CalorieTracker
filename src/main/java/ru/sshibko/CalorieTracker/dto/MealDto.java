package ru.sshibko.CalorieTracker.dto;

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
