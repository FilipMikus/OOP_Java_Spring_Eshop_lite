package sk.stuba.fei.uim.oop.assignment3.eshop.service.cart;

import sk.stuba.fei.uim.oop.assignment3.eshop.controller.cart.request_body.ProductInCartRequestBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectBadRequestException;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectDoesNotExistException;


public interface ICartService {
    Cart createCart();

    Cart getCart(Long id) throws ObjectDoesNotExistException;

    void deleteCart(Long id) throws ObjectDoesNotExistException;

    Cart addProductToCart(Long id, ProductInCartRequestBody requestBody)
            throws ObjectDoesNotExistException, ObjectBadRequestException;

    Double getCartPrice(Long id) throws ObjectDoesNotExistException, ObjectBadRequestException;
}
