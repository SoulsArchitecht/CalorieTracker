package ru.sshibko.CalorieTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportDto {

    private final double totalCaloriesForDay;

    @Override
    public String toString() {
        return "ReportDto{" +
                "totalCaloriesForDay=" + totalCaloriesForDay +
                '}';
    }
}
