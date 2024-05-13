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
            Document doc = Jsoup.connect("https://menunedeli.ru/chto-prigotovit-na-obed-i-uzhin/sladosti/torty/?ysclid=lv26ds32yt839374533").get();
            Elements recipeElements = doc.getElementsByClass("main-post-lst");
            Element cakes = recipeElements.get(0);
            Elements cakeElements = cakes.getElementsByClass("post-card-in-lst row no-gutters");
            for (Element recipeElement : cakeElements) {
                Element metaTag = recipeElement.select("meta[itemprop=name]").first();
                String name = metaTag.attr("content");
                String description = "";
                try {
                    description = recipeElement.selectFirst(".part-1").text();
                }catch (NullPointerException e){
                    description = "Вкусный торт";
                }

                String ingredients = "";

                Elements ingredientElements = recipeElement.getElementsByClass("ingredients-lst");

                for (Element ingredientElement : ingredientElements) {
                    String ingredient = ingredientElement.select(".name").text();
                    ingredients += ingredient + " ";
                }
                Recipe recipe = new Recipe(name, description.getBytes(), ingredients.toString().getBytes());
                listRecipes.add(recipe);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listRecipes;
    }
}
