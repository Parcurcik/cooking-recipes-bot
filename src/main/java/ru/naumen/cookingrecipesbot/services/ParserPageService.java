package ru.naumen.cookingrecipesbot.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import ru.naumen.cookingrecipesbot.domains.Recipe;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class ParserPageService {

    public ArrayList<Recipe> getAllRecipes() throws IOException {
        ArrayList<Recipe> listRecipes = new ArrayList<>();
        try {
            Document doc = Jsoup.connect("https://www.russianfood.com/recipes/bytype/?fid=39").get();
            Elements elements = doc.getElementsByClass("recipe_list_new recipe_list_new2");
            Element cakes = elements.get(0);
            Elements cakeElements = cakes.getElementsByClass("in_seen");
            for (Element cake : cakeElements) {
                Element titleElement = cake.selectFirst("div.title_o");
                String name = titleElement.select("h3").text();
                String description = cake.select("div.announce").text();
                String ingredients = cake.select("div.announce_o").select("div.announce_sub").text();
                Recipe recipe = new Recipe(name, description.getBytes(), ingredients.getBytes());
                listRecipes.add(recipe);
            }
            return listRecipes;
        } catch (Exception e) {
            return getAllRecipes();
        }
    }
}
