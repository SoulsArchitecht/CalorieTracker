package ru.sshibko.CalorieTracker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sshibko.CalorieTracker.dto.InDailyLimitDto;
import ru.sshibko.CalorieTracker.dto.ReportDto;
import ru.sshibko.CalorieTracker.service.ReportService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/daily")
    public ReportDto getDailyCalories(@RequestParam Long userId, @RequestParam String date) {
        return reportService.getTotalCaloriesForDay(userId, LocalDateTime.parse(date));
    }

    @GetMapping("/check-limit")
    public InDailyLimitDto checkDailyLimit(@RequestParam Long userId, @RequestParam String date) {
        return reportService.isWithinDailyLimit(userId, LocalDateTime.parse(date));
    }
}
