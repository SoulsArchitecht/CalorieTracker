package ru.sshibko.CalorieTracker.dto;

import lombok.*;
import ru.sshibko.CalorieTracker.model.Meal;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyHistoryDto {

    private Long userId;
    private List<Meal> meals;

    @Override
    public String toString() {
        return "DailyHistoryDto{" +
                "meals=" + meals +
                '}';
    }
}
