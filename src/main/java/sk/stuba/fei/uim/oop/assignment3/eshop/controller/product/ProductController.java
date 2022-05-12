package sk.stuba.fei.uim.oop.assignment3.eshop.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.request_body.AmountProductRequestBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.request_body.ProductRequestBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.request_body.UpdateProductRequestBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.response_body.AmountProductResponseBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.response_body.ProductResponseBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.service.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectDoesNotExistException;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.product.Product;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService service;
    @Autowired
    private ProductFormator formator;

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<ProductResponseBody>> getAllProducts() {
        List<Product> tmpProductList = service.getAllProducts();
        return new ResponseEntity<>(formator.toResponseBodyList(tmpProductList), HttpStatus.OK);
    }

    @PostMapping()
    @ResponseBody
    public ResponseEntity<ProductResponseBody> createProduct(@RequestBody ProductRequestBody requestBody) {
        Product tmpProduct = service.createProduct(requestBody);
        return new ResponseEntity<>(formator.toResponseBody(tmpProduct), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductResponseBody> getProduct(@PathVariable("id") Long id) {
        try {
            Product tmpProduct = service.getProduct(id);
            return new ResponseEntity<>(formator.toResponseBody(tmpProduct), HttpStatus.OK);
        } catch (ObjectDoesNotExistException objectDoesNotExistException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<ProductResponseBody> updateProduct(@PathVariable("id") Long id,
                                                             @RequestBody UpdateProductRequestBody requestBody) {
        try {
            Product tmpProduct = service.updateProduct(id, requestBody);
            return new ResponseEntity<>(formator.toResponseBody(tmpProduct), HttpStatus.OK);
        } catch (ObjectDoesNotExistException objectDoesNotExistException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long id) {
        try {
            service.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectDoesNotExistException objectDoesNotExistException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/amount")
    @ResponseBody
    public ResponseEntity<AmountProductResponseBody> getProductAmount(@PathVariable("id") Long id) {
        try {
            Product tmpProduct = service.getProductAmount(id);
            return new ResponseEntity<>(formator.toAmountResponseBody(tmpProduct), HttpStatus.OK);
        } catch (ObjectDoesNotExistException objectDoesNotExistException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/amount")
    @ResponseBody
    public ResponseEntity<AmountProductResponseBody> addProductAmount(@PathVariable("id") Long id,
                                                                      @RequestBody
                                                                              AmountProductRequestBody requestBody) {
        try {
            Product tmpProduct = service.incrementProductAmount(id, requestBody);
            return new ResponseEntity<>(formator.toAmountResponseBody(tmpProduct), HttpStatus.OK);
        } catch (ObjectDoesNotExistException objectDoesNotExistException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
