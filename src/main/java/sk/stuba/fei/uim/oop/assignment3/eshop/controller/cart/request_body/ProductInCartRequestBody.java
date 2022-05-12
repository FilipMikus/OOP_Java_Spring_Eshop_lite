package sk.stuba.fei.uim.oop.assignment3.eshop.controller.cart.request_body;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductInCartRequestBody {
    private Long productId;
    private Long amount;
}
