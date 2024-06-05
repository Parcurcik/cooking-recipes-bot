package ru.naumen.cookingrecipesbot.domains;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@Table(name = "shopping_lists")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_list_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "number_recipe")
    private Long number_recipe;

    public ShoppingList(Long userId, String name, Long number_recipe) {
        this.userId = userId;
        this.name = name;
        this.number_recipe = number_recipe;
    }

    public ShoppingList() {

    }
}


