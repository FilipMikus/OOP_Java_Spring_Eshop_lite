package sk.stuba.fei.uim.oop.assignment3.eshop.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.request_body.AmountProductRequestBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.request_body.UpdateProductRequestBody;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectBadRequestException;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectDoesNotExistException;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.product.ProductRepository;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.product.Product;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.request_body.ProductRequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(ProductRequestBody requestBody) {
        Product tmpProduct = new Product();
        tmpProduct.setName(requestBody.getName());
        tmpProduct.setDescription(requestBody.getDescription());
        tmpProduct.setAmount(requestBody.getAmount());
        tmpProduct.setUnit(requestBody.getUnit());
        tmpProduct.setPrice(requestBody.getPrice());
        return productRepository.save(tmpProduct);
    }

    @Override
    public Product getProduct(Long id) throws ObjectDoesNotExistException {
        Optional<Product> tmpProduct = productRepository.findById(id);
        if (tmpProduct.equals(Optional.empty()))
            throw new ObjectDoesNotExistException();
        else
            return tmpProduct.get();
    }

    @Override
    public Product updateProduct(Long id, UpdateProductRequestBody requestBody) throws ObjectDoesNotExistException {
        Product product = this.getProduct(id);
        this.updateProductAttributes(product, requestBody);
        return productRepository.save(product);
    }

    private void updateProductAttributes(Product product, UpdateProductRequestBody requestBody) {
        if (!requestBody.isNameNull())
            product.setName(requestBody.getName());
        if (!requestBody.isDescriptionNull())
            product.setDescription(requestBody.getDescription());
    }

    @Override
    public void deleteProduct(Long id) throws ObjectDoesNotExistException {
        Optional<Product> tmpProduct = productRepository.findById(id);
        if (tmpProduct.equals(Optional.empty()))
            throw new ObjectDoesNotExistException();
        else
            productRepository.deleteById(id);
    }

    @Override
    public Product getProductAmount(Long id) throws ObjectDoesNotExistException {
        return this.getProduct(id);
    }

    @Override
    public Product incrementProductAmount(Long id, AmountProductRequestBody requestBody) throws ObjectDoesNotExistException {
        Product product = this.getProduct(id);
        Long newAmount = product.getAmount() + requestBody.getAmount();
        product.setAmount(newAmount);
        return productRepository.save(product);
    }

    @Override
    public void decrementProductAmountAndUpdate(Product product, Long amount)
            throws ObjectBadRequestException {
        Long productAmount = product.getAmount();
        if (productAmount < amount)
            throw new ObjectBadRequestException();
        else {
            Long newAmount = productAmount - amount;
            product.setAmount(newAmount);
            productRepository.save(product);
        }
    }
}
