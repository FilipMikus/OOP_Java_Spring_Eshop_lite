package sk.stuba.fei.uim.oop.assignment3.eshop.controller.cart;

import org.springframework.stereotype.Component;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.cart.response_body.CartResponseBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.cart.response_body.ProductInCartResponseBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.product_in_cart.ProductInCart;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartFormator {
    public CartResponseBody toResponseBody(Cart cart) {
        CartResponseBody cartResponseBody = new CartResponseBody();
        cartResponseBody.setId(cart.getId());
        cartResponseBody.setShoppingList(this.toProductInCartResponseBodyList(cart.getShoppingList()));
        cartResponseBody.setPayed(cart.getPayed());
        return cartResponseBody;
    }

    private List<ProductInCartResponseBody> toProductInCartResponseBodyList(List<ProductInCart> productInCartList) {
        List<ProductInCartResponseBody> productInCartResponseBodyList = new ArrayList<>();
        if (productInCartList != null)
            for (ProductInCart productInCart : productInCartList)
                productInCartResponseBodyList.add(new ProductInCartResponseBody(productInCart.getProduct().getId(),
                        productInCart.getAmount()));
        return productInCartResponseBodyList;
    }
}
