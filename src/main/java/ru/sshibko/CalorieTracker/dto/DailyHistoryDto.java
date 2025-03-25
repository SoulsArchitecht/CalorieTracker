package ru.sshibko.CalorieTracker.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyHistoryDto {

    private Long userId;
    private List<DailyMealsDto> dailyMeals;
}
