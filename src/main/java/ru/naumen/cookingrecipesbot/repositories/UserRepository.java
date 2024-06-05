package ru.naumen.cookingrecipesbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumen.cookingrecipesbot.domains.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
