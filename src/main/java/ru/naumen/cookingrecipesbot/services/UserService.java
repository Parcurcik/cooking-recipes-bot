package ru.naumen.cookingrecipesbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumen.cookingrecipesbot.domains.ShoppingList;
import ru.naumen.cookingrecipesbot.domains.User;
import ru.naumen.cookingrecipesbot.repositories.UserRepository;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private final UserRepository userRepository;

    /**
     * Добавить пользователя из таблицы users
     * @param user
     */
    public void addUser(User user) {
        userRepository.save(user);
    }

    /**
     * Удалить пользователся из таблицы users
     * @param chatId
     */
    public void removeUser(long chatId) {
        for(User user : userRepository.findAll()){
            if(user.getUser_id().equals(chatId)){
                userRepository.delete(user);
            }
        }
    }

}
