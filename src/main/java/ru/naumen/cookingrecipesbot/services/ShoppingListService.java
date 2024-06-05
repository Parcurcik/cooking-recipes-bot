package ru.naumen.cookingrecipesbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumen.cookingrecipesbot.domains.Recipe;
import ru.naumen.cookingrecipesbot.domains.ShoppingList;
import ru.naumen.cookingrecipesbot.domains.User;
import ru.naumen.cookingrecipesbot.repositories.ShoppingListRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListService {
    private final ShoppingListRepository shoppingListRepository;
    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository){
        this.shoppingListRepository = shoppingListRepository;
    }

    /**
     * Добавить рецепт в список тортов клиента
     * @param shoppingList
     * @throws IOException
     */
    public void addRecipe(ShoppingList shoppingList) throws IOException {
        for (ShoppingList sl : shoppingListRepository.findAll()) {
            if (sl.getNumber_recipe().equals(shoppingList.getNumber_recipe())) {
                return;
            }
        }
        shoppingListRepository.save(shoppingList);
    }

    /**
     * Очистить список добавленных пользователем тортов
     * @throws IOException
     */
    public void clearList() throws IOException {
        shoppingListRepository.deleteAll();
    }

    /**
     * Получить номера добавленных тортов
     * @param userId
     * @return
     * @throws IOException
     */
    public ArrayList<Long> getAddNumbersRecipes(Long userId) throws IOException {
        ArrayList<Long> numberResipes = new ArrayList<>();
        for(ShoppingList element : shoppingListRepository.findAll()){
            if(element.getUserId().equals(userId)){
                numberResipes.add(element.getNumber_recipe());
            }
        }
        return numberResipes;
    }

    public void removeUserRecipes(Long userId) throws IOException {
        for(ShoppingList user : shoppingListRepository.findAll()){
            if(user.getUserId().equals(userId)){
                shoppingListRepository.delete(user);
            }
        }
    }
}
