package sk.stuba.fei.uim.oop.assignment3.eshop.controller.cart.response_body;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CartResponseBody {
    private Long id;
    private List<ProductInCartResponseBody> shoppingList;
    private Boolean payed;
}
