package ru.naumen.cookingrecipesbot.models;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "shopping_lists")
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_list_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "products")
    private String products;

}


