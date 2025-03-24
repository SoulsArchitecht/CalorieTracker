package ru.sshibko.CalorieTracker.service;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.sshibko.CalorieTracker.dto.UserDto;
import ru.sshibko.CalorieTracker.exception.ResourceNotFoundException;
import ru.sshibko.CalorieTracker.mapper.UserMapper;
import ru.sshibko.CalorieTracker.model.User;
import ru.sshibko.CalorieTracker.model.enums.Gender;
import ru.sshibko.CalorieTracker.model.enums.Goal;
import ru.sshibko.CalorieTracker.repository.UserRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserService implements CRUDService<UserDto> {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    @Override
    public UserDto getById(Long id) {
        log.info("User getting by ID: {} ", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Account with given id: " + id + " is not exists"));
        return userMapper.mapToDto(user);
    }

    @Override
    public Collection<UserDto> getAll() {
        log.info("Getting all users");
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::mapToDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDto create(@Valid UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
        user.setDailyCalories(calculateDailyCalories(user));
        User savedUser = userRepository.save(user);
        log.info("User with id: {} created successfully", savedUser.getId());
        return userMapper.mapToDto(savedUser);
    }

    @Override
    @Transactional
    public UserDto update(@Valid Long id, @Valid UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + id));
        user.setUsername(userDto.getUsername());
        user.setAge(userDto.getAge());
        user.setEmail(userDto.getEmail());
        user.setGender(Gender.valueOf(userDto.getGender()));
        user.setWeight(userDto.getWeight());
        user.setHeight(userDto.getHeight());
        user.setGoal(Goal.valueOf(userDto.getGoal()));
        user.setDailyCalories(calculateDailyCalories(userMapper.mapToEntity(userDto)));

        User updatedUser = userRepository.save(user);
        return userMapper.mapToDto(updatedUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
        log.info("User with id: {} deleted successfully", id);
    }

    private double calculateDailyCalories(User user) {
        double bmr = 0;
        if (user.getGender() == Gender.MALE) {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;
        } else if (user.getGender() == Gender.FEMALE) {
            bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() - 161;
        }

        return switch (user.getGoal()) {
            case WEIGHT_LOSS -> bmr * 0.8;
            case WEIGHT_GAIN -> bmr * 1.2;
            default -> bmr;
        };
    }
}
