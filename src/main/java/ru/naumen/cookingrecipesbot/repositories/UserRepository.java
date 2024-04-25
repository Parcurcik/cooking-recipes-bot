package ru.naumen.cookingrecipesbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.cookingrecipesbot.domains.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
