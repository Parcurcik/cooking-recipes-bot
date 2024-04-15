package ru.naumen.cookingrecipesbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumen.cookingrecipesbot.domains.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}