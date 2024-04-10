package ru.naumen.cookingrecipesbot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.naumen.cookingrecipesbot.models.Recipe;
import ru.naumen.cookingrecipesbot.repositories.RecipeRepository;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final ParserPageService parserPageService;
    public final RecipeRepository recipeRepository;

    public void saveAllRecipesFromPage() throws IOException {
        List<Recipe> listRecipes = parserPageService.getAllRecipes();
        recipeRepository.saveAll(listRecipes);
    }
}
