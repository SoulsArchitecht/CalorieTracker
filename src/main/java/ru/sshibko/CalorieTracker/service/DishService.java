package ru.sshibko.CalorieTracker.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.sshibko.CalorieTracker.dto.DishDto;
import ru.sshibko.CalorieTracker.exception.ResourceNotFoundException;
import ru.sshibko.CalorieTracker.mapper.DishMapper;
import ru.sshibko.CalorieTracker.model.Dish;
import ru.sshibko.CalorieTracker.repository.DishRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DishService implements CRUDService<DishDto> {

    private final DishRepository dishRepository;

    private final DishMapper dishMapper;

    @Override
    public DishDto getById(Long id) {
        log.info("Getting meal with id: " + id);
        Dish dish = dishRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Dish not found with id: " + id));
        return dishMapper.mapToDto(dish);
    }

    @Override
    public Collection<DishDto> getAll() {
        log.info("Getting all dishes");
        List<Dish> dishes = dishRepository.findAll();
        return dishes.stream().map(dishMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DishDto create(@Valid DishDto dishDto) {
        Dish dish = dishMapper.mapToEntity(dishDto);
        Dish savedDish = dishRepository.save(dish);
        log.info("Saving dish with id: {}", savedDish.getId());
        return dishMapper.mapToDto(savedDish);
    }

    @Override
    @Transactional
    public DishDto update(@Valid Long id, @Valid DishDto dishDto) {
        Dish dish = dishRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Dish not found with id: " + id));
        dish.setName(dishDto.getName());
        dish.setProteins(dishDto.getProteins());
        dish.setFats(dishDto.getFats());
        dish.setCarbohydrates(dishDto.getCarbohydrates());
        dish.setCaloriesPerServing(dishDto.getCaloriesPerServing());

        Dish updatedDish = dishRepository.save(dish);
        log.info("Updating dish with id: {}", updatedDish.getId());
        return dishMapper.mapToDto(updatedDish);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Dish dish = dishRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Dish not found with id: " + id));
        dishRepository.delete(dish);
        log.info("Dish with id: {} deleted successfully", id);
    }
}
