package sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.product_in_cart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInCartRepository extends CrudRepository<ProductInCart, Long> {

}
