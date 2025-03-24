package ru.sshibko.CalorieTracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sshibko.CalorieTracker.dto.DailyHistoryDto;
import ru.sshibko.CalorieTracker.dto.InDailyLimitDto;
import ru.sshibko.CalorieTracker.dto.ReportDto;
import ru.sshibko.CalorieTracker.service.ReportService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/{userId}/daily")
    public ReportDto getDailyCalories(@PathVariable Long userId, @RequestParam String date) {
        return reportService.getTotalCaloriesForDay(userId, LocalDateTime.parse(date));
    }

    @GetMapping("/{userId}/check-limit")
    public InDailyLimitDto checkDailyLimit(@PathVariable Long userId, @RequestParam String date) {
        return reportService.isWithinDailyLimit(userId, LocalDateTime.parse(date));
    }

    @GetMapping("/{userId}/history")
    public DailyHistoryDto getHistory(@PathVariable Long userId) {
        return reportService.getDailyHistory(userId);
    }
}
