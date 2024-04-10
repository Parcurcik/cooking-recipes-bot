package ru.naumen.cookingrecipesbot.models;
import jakarta.persistence.*;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Entity
@Data
@Table(name = "recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private byte[] description;

    @Column(name = "ingredients")
    private byte[] ingredients;

    public Recipe(String name, byte[] description, byte[] ingredients) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
    }

    public Recipe() {

    }
}

