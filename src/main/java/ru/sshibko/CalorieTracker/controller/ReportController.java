package ru.sshibko.CalorieTracker.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Report Controller", description = "Операции с отчетами по приемам пищи и потреблению каллрий")
public class ReportController {

    private final ReportService reportService;

    @Operation(summary = "Показать дневное потребление калорий по указанной дате")
    @GetMapping("/{userId}/daily")
    public ReportDto getDailyCalories(@PathVariable Long userId, @RequestParam String date) {
        return reportService.getTotalCaloriesForDay(userId, LocalDateTime.parse(date));
    }

    @Operation(summary = "Показать уложился ли пользователь в дневную норму в указанную дату")
    @GetMapping("/{userId}/check-limit")
    public InDailyLimitDto checkDailyLimit(@PathVariable Long userId, @RequestParam String date) {
        return reportService.isWithinDailyLimit(userId, LocalDateTime.parse(date));
    }

    @Operation(summary = "Показать историю питания пользователя по дням")
    @GetMapping("/{userId}/history")
    public DailyHistoryDto getHistory(@PathVariable Long userId) {
        return reportService.getDailyHistoryForDay(userId);
    }
}
