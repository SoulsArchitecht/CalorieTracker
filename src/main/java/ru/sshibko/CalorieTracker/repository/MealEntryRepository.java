package ru.sshibko.CalorieTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sshibko.CalorieTracker.model.MealEntry;

@Repository
public interface MealEntryRepository extends JpaRepository<MealEntry, Long> {
}
