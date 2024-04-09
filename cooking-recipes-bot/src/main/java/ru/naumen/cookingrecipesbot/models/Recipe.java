package ru.naumen.cookingrecipesbot.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "recipes")
public class Recipe {
    public Recipe(Long id, String name, String description, String ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "ingredients")
    private String ingredients;

    public Recipe() {

    }

    public Recipe(String name, String description, String ingredients) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }
}

