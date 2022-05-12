package sk.stuba.fei.uim.oop.assignment3.eshop.service.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.cart.request_body.ProductInCartRequestBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.CartRepository;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.product_in_cart.ProductInCart;
import sk.stuba.fei.uim.oop.assignment3.eshop.service.cart.product_in_cart.IProductInCartService;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectBadRequestException;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectDoesNotExistException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private IProductInCartService service;

    @Override
    public Cart createCart() {
        Cart tmpCart = new Cart();
        tmpCart.setShoppingList(new ArrayList<>());
        tmpCart.setPayed(false);
        return cartRepository.save(tmpCart);
    }


    @Override
    public Cart getCart(Long id) throws ObjectDoesNotExistException {
        Optional<Cart> tmpCart = cartRepository.findById(id);
        if (tmpCart.equals(Optional.empty()))
            throw new ObjectDoesNotExistException();
        else
            return tmpCart.get();
    }

    @Override
    public Cart addProductToCart(Long id, ProductInCartRequestBody requestBody)
            throws ObjectDoesNotExistException, ObjectBadRequestException {
        this.amountValidate(requestBody);
        Cart tmpCart = this.getCart(id);
        this.isCartPayed(tmpCart);
        this.updateShoppingList(tmpCart, requestBody);
        return cartRepository.save(tmpCart);
    }

    @Override
    public void deleteCart(Long id) throws ObjectDoesNotExistException {
        Optional<Cart> tmpProduct = cartRepository.findById(id);
        if (tmpProduct.equals(Optional.empty()))
            throw new ObjectDoesNotExistException();
        else
            cartRepository.deleteById(id);
    }

    @Override
    public Double getCartPrice(Long id) throws ObjectDoesNotExistException, ObjectBadRequestException {
        Cart tmpCart = this.getCart(id);
        if (tmpCart.getPayed())
            throw new ObjectBadRequestException();
        tmpCart.setPayed(true);
        cartRepository.save(tmpCart);
        return this.sumOfShoppingListPrice(tmpCart);
    }

    private void updateShoppingList(Cart cart, ProductInCartRequestBody requestBody)
            throws ObjectBadRequestException, ObjectDoesNotExistException {
        List<ProductInCart> shoppingList = cart.getShoppingList();
        for (ProductInCart productInCart : shoppingList) {
            Long currentProductId = productInCart.getProduct().getId();
            Long searchedProductId = requestBody.getProductId();
            if (isSearchedProduct(currentProductId, searchedProductId)) {
                service.updateProductInCart(productInCart, requestBody.getAmount());
                return;
            }
        }
        ProductInCart tmpProductInCart = service.createProductInCart(cart, requestBody.getProductId(), requestBody.getAmount());
        shoppingList.add(tmpProductInCart);
    }

    private void isCartPayed(Cart cart) throws ObjectBadRequestException {
        if (cart.getPayed())
            throw new ObjectBadRequestException();
    }

    private void amountValidate(ProductInCartRequestBody requestBody) throws ObjectBadRequestException {
        if (requestBody.getAmount() < 0)
            throw new ObjectBadRequestException();
    }

    private Boolean isSearchedProduct(Long currentProductId, Long searchedProductId) {
        return currentProductId.equals(searchedProductId);
    }

    private Double sumOfShoppingListPrice(Cart cart) {
        double sum = 0.0;
        for (ProductInCart productInCart : cart.getShoppingList()) {
            sum += service.getProductInCartPrice(productInCart);
        }
        return sum;
    }
}
