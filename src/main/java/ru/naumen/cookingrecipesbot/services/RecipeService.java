package ru.naumen.cookingrecipesbot.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.naumen.cookingrecipesbot.domains.Recipe;
import ru.naumen.cookingrecipesbot.repositories.RecipeRepository;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {


    private final ParserPageService parserPageService;
    private final RecipeRepository recipeRepository;

    public void saveAllRecipesFromPage() throws IOException {
        List<Recipe> listRecipes = parserPageService.getAllRecipes();
        recipeRepository.saveAll(listRecipes);
    }

    public List<Recipe> getAll(){
        return recipeRepository.findAll();
    }

}
