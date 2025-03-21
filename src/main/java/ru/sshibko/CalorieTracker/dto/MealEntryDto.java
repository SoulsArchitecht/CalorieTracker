package ru.sshibko.CalorieTracker.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealEntryDto {

    private Long userId;
    private Long mealId;
    private LocalDateTime timestamp;
    private int quantity;
}
