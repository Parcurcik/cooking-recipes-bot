package ru.naumen.cookingrecipesbot.domains;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_recipe")
    private Long number_recipe;

    public User(Long user_id, String name, Long number_recipe) {
        this.user_id = user_id;
        this.name = name;
        this.number_recipe = number_recipe;
    }

    public User() {

    }
}
