package ru.sshibko.CalorieTracker.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportDto {

    private Long userId;
    private LocalDateTime timestamp;
    private int totalCaloriesForDay;

    @Override
    public String toString() {
        return "ReportDto{" +
                "userId=" + userId +
                ", timestamp=" + timestamp +
                ", totalCaloriesForDay=" + totalCaloriesForDay +
                '}';
    }
}
