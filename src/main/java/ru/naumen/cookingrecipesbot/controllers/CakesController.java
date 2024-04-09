package ru.naumen.cookingrecipesbot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.naumen.cookingrecipesbot.services.RecipeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cakes")
public class CakesController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/recipes")
    public List<String> getRecipes() {
        List<String> answer = new ArrayList<>();
        try {
            // Загружаем HTML страницу с сайта
            Document doc = Jsoup.connect("https://www.russianfood.com/recipes/bytype/?fid=39").get();

            // Находим все элементы с классом "recipe_l", где находится информация о каждом торте
            Elements elements = doc.getElementsByClass("recipe_l");

            // Парсим информацию о каждом торте
            for (Element element : elements) {
                // Название торта
                String name = element.select("span[itemprop=name]").text();

                // Описание торта
                String description = element.select("div[class=announce]").select("p[itemprop=description]").text();

                // Ингредиенты торта
                String ingredients = element.select("div[class=ingr_str]").text();

                // Сохраняем рецепт в базу данных
                recipeService.saveRecipe(name, description, ingredients);

                // Добавляем название рецепта в список ответа
                answer.add(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return answer;
    }
}
