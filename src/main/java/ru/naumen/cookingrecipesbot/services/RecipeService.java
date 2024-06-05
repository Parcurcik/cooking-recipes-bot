package ru.naumen.cookingrecipesbot.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.naumen.cookingrecipesbot.domains.Recipe;
import ru.naumen.cookingrecipesbot.repositories.RecipeRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeService {


    private final ParserPageService parserPageService;
    private final RecipeRepository recipeRepository;

    public void saveAllRecipesFromPage() throws IOException {
        List<Recipe> listRecipes = parserPageService.getAllRecipes();
        recipeRepository.saveAll(listRecipes);
    }

    /**
     * Получить рецепт торта по id
     * @param id
     * @return
     */
    public Optional<Recipe> getRecipeById(Long id) {
       return recipeRepository.findById(id);
    }

    /**
     * Получить все имеющиеся рецепты
     * @return
     */
    public List<Recipe> getAll(){
        return recipeRepository.findAll();
    }

}
