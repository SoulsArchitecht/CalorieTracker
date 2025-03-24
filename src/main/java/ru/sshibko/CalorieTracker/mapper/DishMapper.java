package ru.sshibko.CalorieTracker.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.sshibko.CalorieTracker.dto.DishDto;
import ru.sshibko.CalorieTracker.model.Dish;

@Component
@RequiredArgsConstructor
public class DishMapper {

    public Dish mapToEntity(DishDto dishDto) {
        return Dish.builder()
                .name(dishDto.getName())
                .caloriesPerServing(dishDto.getCaloriesPerServing())
                .proteins(dishDto.getProteins())
                .fats(dishDto.getFats())
                .carbohydrates(dishDto.getCarbohydrates())
                .build();
    }

    public DishDto mapToDto(Dish dish) {
        return DishDto.builder()
                .name(dish.getName())
                .caloriesPerServing(dish.getCaloriesPerServing())
                .proteins(dish.getProteins())
                .fats(dish.getFats())
                .carbohydrates(dish.getCarbohydrates())
                .build();
    }
}
