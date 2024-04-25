package ru.naumen.cookingrecipesbot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.cookingrecipesbot.domains.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
}
