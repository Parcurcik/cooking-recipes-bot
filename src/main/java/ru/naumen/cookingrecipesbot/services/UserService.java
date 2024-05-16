package ru.naumen.cookingrecipesbot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void addUser(User user) {
        userRepository.save(user);
//        for(User lastUser: userRepository.findAllById(Collections.singleton(user.getUser_id()))){
//            lastUser.setNumber_recipe(user.getNumber_recipe());
//        }
    }

//    public Long getLastRecipeId(User user){
//        for (int i = 0; i < userRepository.)
//        userRepository.findAllById(user.getUser_id())
//    }
}
