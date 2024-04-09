package ru.naumen.cookingrecipesbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumen.cookingrecipesbot.models.Recipe;
import ru.naumen.cookingrecipesbot.repositories.RecipeRepository;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    @Autowired
    public RecipeService(RecipeRepository recipeRepository){this.recipeRepository=recipeRepository;}

    public void saveRecipe(String name, String description, String ingredients){
        Recipe recipe = new Recipe(name, description, ingredients);
        recipeRepository.save(recipe);
    }
}
