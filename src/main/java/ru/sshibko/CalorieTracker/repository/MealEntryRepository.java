package ru.sshibko.CalorieTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealEntryRepository extends JpaRepository<MealEntryRepository, Long> {
}
