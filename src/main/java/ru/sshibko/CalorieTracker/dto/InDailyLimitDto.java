package ru.sshibko.CalorieTracker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InDailyLimitDto {

    private boolean inDailyLimit;

    @Override
    public String toString() {
        return "InDailyLimitDto{" +
                "inDailyLimit=" + inDailyLimit +
                '}';
    }
}
