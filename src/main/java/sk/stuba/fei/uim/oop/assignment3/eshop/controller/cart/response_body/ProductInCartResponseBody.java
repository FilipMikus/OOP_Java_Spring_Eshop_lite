package sk.stuba.fei.uim.oop.assignment3.eshop.controller.cart.response_body;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ProductInCartResponseBody {
    private Long productId;
    private Long amount;
}
