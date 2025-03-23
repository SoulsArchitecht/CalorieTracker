package ru.sshibko.CalorieTracker.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.sshibko.CalorieTracker.dto.MealEntryDto;
import ru.sshibko.CalorieTracker.exception.ResourceNotFoundException;
import ru.sshibko.CalorieTracker.mapper.MealEntryMapper;
import ru.sshibko.CalorieTracker.model.Meal;
import ru.sshibko.CalorieTracker.model.MealEntry;
import ru.sshibko.CalorieTracker.model.User;
import ru.sshibko.CalorieTracker.repository.MealEntryRepository;
import ru.sshibko.CalorieTracker.repository.MealRepository;
import ru.sshibko.CalorieTracker.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MealEntryService implements CRUDService<MealEntryDto> {

    private final MealEntryRepository mealEntryRepository;

    private final MealEntryMapper mealEntryMapper;

    private final MealRepository mealRepository;

    private final UserRepository userRepository;

    @Override
    public MealEntryDto getById(Long id) {
        log.info("Getting mealEntry with id: " + id);
        MealEntry mealEntry = mealEntryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MealEntry not found with id: " + id));
        return mealEntryMapper.mapToDto(mealEntry);
    }

    @Override
    public Collection<MealEntryDto> getAll() {
        log.info("Getting all mealEntries");
        List<MealEntry> mealsEntries = mealEntryRepository.findAll();
        return mealsEntries.stream().map(mealEntryMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public MealEntryDto create(MealEntryDto mealEntryDto) {
        MealEntry mealEntry = mealEntryMapper.mapToEntity(mealEntryDto);
        MealEntry savedMealEntry = mealEntryRepository.save(mealEntry);
        log.info("Saving mealEntry with id: {}", savedMealEntry.getId());
        return mealEntryMapper.mapToDto(savedMealEntry);
    }

    @Override
    @Transactional
    public MealEntryDto update(Long id, MealEntryDto mealEntryDto) {
        MealEntry mealEntry = mealEntryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MealEntry not found with id: " + id));
        Meal meal = mealRepository.findById(mealEntryDto.getMealId()).orElseThrow(
                () -> new ResourceNotFoundException("Meal not found with id: " + id));
        User user = userRepository.findById(mealEntryDto.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + id));

        mealEntry.setMeal(meal);
        mealEntry.setUser(user);
        mealEntry.setTimestamp(mealEntryDto.getTimestamp());
        mealEntry.setQuantity(mealEntryDto.getQuantity());

        MealEntry updatedMealEntry = mealEntryRepository.save(mealEntry);
        log.info("Updating mealEntry with id: {}", updatedMealEntry.getId());
        return mealEntryMapper.mapToDto(updatedMealEntry);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        MealEntry mealEntry = mealEntryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("MealEntry not found with id: " + id));
        mealEntryRepository.delete(mealEntry);
        log.info("MealEntry with id: {} deleted successfully", id);
    }
}
