package ru.sshibko.CalorieTracker.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sshibko.CalorieTracker.dto.MealDto;
import ru.sshibko.CalorieTracker.model.Meal;

@Component
@RequiredArgsConstructor
public class MealMapper {

    public Meal mapToEntity(MealDto mealDto) {
        return Meal.builder()
                .name(mealDto.getName())
                .caloriesPerServing(mealDto.getCaloriesPerServing())
                .proteins(mealDto.getProteins())
                .fats(mealDto.getFats())
                .carbohydrates(mealDto.getCarbohydrates())
                .build();
    }

    public MealDto mapToDto(Meal meal) {
        return MealDto.builder()
                .name(meal.getName())
                .caloriesPerServing(meal.getCaloriesPerServing())
                .proteins(meal.getProteins())
                .fats(meal.getFats())
                .carbohydrates(meal.getCarbohydrates())
                .build();
    }
}
