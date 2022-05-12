package sk.stuba.fei.uim.oop.assignment3.eshop.model.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.product_in_cart.ProductInCart;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(orphanRemoval = true)
    private List<ProductInCart> shoppingList;
    private Boolean payed;
}
