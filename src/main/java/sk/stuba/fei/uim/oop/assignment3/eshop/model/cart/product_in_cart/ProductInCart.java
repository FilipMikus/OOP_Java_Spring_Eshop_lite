package sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.product_in_cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.product.Product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Product product;
    private Long amount;
}
