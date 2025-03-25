package ru.sshibko.CalorieTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sshibko.CalorieTracker.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    String queryByUserAndDate =
            "SELECT * FROM meals m WHERE m.user_id = :userId AND DATE(m.timestamp) = DATE(:date)";
    String queryByUserId =
            "SELECT * FROM meals m WHERE m.user_id = :userId";
    String queryGroupingByUserIdAndDate =
            "SELECT * FROM meals m WHERE m.user_id = :userId ORDER BY DATE(m.timestamp) DESC, m.timestamp DESC";

    @Query(value = queryByUserAndDate, nativeQuery = true)
    List<Meal> findByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDateTime date);

    @Query(value = queryByUserId, nativeQuery = true)
    List<Meal> findAllByUserId(@Param("userId") Long userId);

    @Query(value = queryGroupingByUserIdAndDate, nativeQuery = true)
    List<Meal> findAllByUserIdGroupingDate(@Param("userId") Long userId);
}
