package ru.sshibko.CalorieTracker.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InDailyLimitDto {

    private Long userId;
    private LocalDateTime timestamp;
    private boolean inDailyLimit;

    @Override
    public String toString() {
        return "InDailyLimitDto{" +
                "userId=" + userId +
                ", timestamp=" + timestamp +
                ", inDailyLimit=" + inDailyLimit +
                '}';
    }
}
