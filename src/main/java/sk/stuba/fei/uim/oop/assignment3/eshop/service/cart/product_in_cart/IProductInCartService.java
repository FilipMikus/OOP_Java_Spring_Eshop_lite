package sk.stuba.fei.uim.oop.assignment3.eshop.service.cart.product_in_cart;

import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.product_in_cart.ProductInCart;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectBadRequestException;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectDoesNotExistException;

public interface IProductInCartService {
    void updateProductInCart(ProductInCart productInCart, Long productInCartAmount)
            throws ObjectBadRequestException;

    ProductInCart createProductInCart(Cart cart, Long productId, Long productInCartAmount)
            throws ObjectBadRequestException, ObjectDoesNotExistException;

    Double getProductInCartPrice(ProductInCart productInCart);
}
