package ru.sshibko.CalorieTracker.model;

import jakarta.persistence.*;
import lombok.*;
import ru.sshibko.CalorieTracker.model.enums.Gender;
import ru.sshibko.CalorieTracker.model.enums.Goal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "age", nullable = false)
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender; // need for formula

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "height", nullable = false)
    private double height;

    @Enumerated(EnumType.STRING)
    @Column(name = "goal")
    private Goal goal;

    @Column(name = "daily_calorie")
    private double dailyCalorie;
}
