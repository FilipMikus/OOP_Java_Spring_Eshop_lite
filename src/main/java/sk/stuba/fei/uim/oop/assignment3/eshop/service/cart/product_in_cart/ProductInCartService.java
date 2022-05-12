package sk.stuba.fei.uim.oop.assignment3.eshop.service.cart.product_in_cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.product_in_cart.ProductInCart;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.product_in_cart.ProductInCartRepository;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.product.Product;
import sk.stuba.fei.uim.oop.assignment3.eshop.service.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectBadRequestException;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectDoesNotExistException;


@Service
public class ProductInCartService implements IProductInCartService {
    @Autowired
    private ProductInCartRepository productInCartRepository;
    @Autowired
    private IProductService service;

    @Override
    public void updateProductInCart(ProductInCart productInCart, Long amount)
            throws ObjectBadRequestException {
        Product tmpProduct = productInCart.getProduct();
        service.decrementProductAmountAndUpdate(tmpProduct, amount);
        this.incrementAmountByReference(productInCart, amount);
        productInCartRepository.save(productInCart);
    }

    @Override
    public ProductInCart createProductInCart(Cart cart, Long productId, Long productInCartAmount)
            throws ObjectBadRequestException, ObjectDoesNotExistException {
        Product tmpProduct = service.getProduct(productId);
        service.decrementProductAmountAndUpdate(tmpProduct, productInCartAmount);
        ProductInCart tmpProductInCart = new ProductInCart();
        tmpProductInCart.setAmount(productInCartAmount);
        tmpProductInCart.setProduct(service.getProduct(productId));
        tmpProductInCart.setCart(cart);
        return productInCartRepository.save(tmpProductInCart);
    }

    @Override
    public Double getProductInCartPrice(ProductInCart productInCart) {
        double sum = 0.0;
        sum = sum + (productInCart.getAmount() * productInCart.getProduct().getPrice());
        return sum;
    }

    private void incrementAmountByReference(ProductInCart productInCart, Long amount) {
        Long newAmount = productInCart.getAmount() + amount;
        productInCart.setAmount(newAmount);
    }
}
