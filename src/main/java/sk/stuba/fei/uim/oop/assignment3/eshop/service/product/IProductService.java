package sk.stuba.fei.uim.oop.assignment3.eshop.service.product;

import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.request_body.AmountProductRequestBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.request_body.ProductRequestBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.request_body.UpdateProductRequestBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.product.Product;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectBadRequestException;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectDoesNotExistException;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product createProduct(ProductRequestBody requestBody);

    Product getProduct(Long id) throws ObjectDoesNotExistException;

    Product updateProduct(Long id, UpdateProductRequestBody requestBody) throws ObjectDoesNotExistException;

    void deleteProduct(Long id) throws ObjectDoesNotExistException;

    Product getProductAmount(Long id) throws ObjectDoesNotExistException;

    Product incrementProductAmount(Long id, AmountProductRequestBody requestBody) throws ObjectDoesNotExistException;

    void decrementProductAmountAndUpdate(Product product, Long amount)
            throws ObjectBadRequestException;
}
