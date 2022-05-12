package sk.stuba.fei.uim.oop.assignment3.eshop.controller.product;

import org.springframework.stereotype.Component;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.product.Product;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.response_body.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductFormator {

    public ProductResponseBody toResponseBody(Product product) {
        ProductResponseBody productResponseBody = new ProductResponseBody();
        productResponseBody.setId(product.getId());
        productResponseBody.setName(product.getName());
        productResponseBody.setDescription(product.getDescription());
        productResponseBody.setAmount(product.getAmount());
        productResponseBody.setUnit(product.getUnit());
        productResponseBody.setPrice(product.getPrice());
        return productResponseBody;
    }

    public List<ProductResponseBody> toResponseBodyList(List<Product> productList) {
        List<ProductResponseBody> responseBodyList = new ArrayList<>();
        for (Product product : productList)
            responseBodyList.add(this.toResponseBody(product));
        return responseBodyList;
    }

    public AmountProductResponseBody toAmountResponseBody(Product product) {
        AmountProductResponseBody amountProductResponseBody = new AmountProductResponseBody();
        amountProductResponseBody.setAmount(product.getAmount());
        return amountProductResponseBody;
    }
}
