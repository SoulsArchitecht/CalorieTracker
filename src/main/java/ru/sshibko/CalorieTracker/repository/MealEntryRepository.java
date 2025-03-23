package ru.sshibko.CalorieTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sshibko.CalorieTracker.model.MealEntry;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealEntryRepository extends JpaRepository<MealEntry, Long> {

    String queryByUserAndDate =
            "SELECT * FROM meal_entries m WHERE m.user_id = :userId AND DATE(m.timestamp) = DATE(:date)";

    @Query(value = queryByUserAndDate, nativeQuery = true)
    List<MealEntry> findByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDateTime date);
}
