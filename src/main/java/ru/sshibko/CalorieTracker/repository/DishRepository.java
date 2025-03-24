package ru.sshibko.CalorieTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sshibko.CalorieTracker.model.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
