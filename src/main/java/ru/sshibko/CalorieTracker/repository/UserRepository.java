package ru.sshibko.CalorieTracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sshibko.CalorieTracker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
