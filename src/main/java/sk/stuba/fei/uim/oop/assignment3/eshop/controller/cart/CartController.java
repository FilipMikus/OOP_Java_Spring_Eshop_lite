package sk.stuba.fei.uim.oop.assignment3.eshop.controller.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.cart.request_body.ProductInCartRequestBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.controller.cart.response_body.CartResponseBody;
import sk.stuba.fei.uim.oop.assignment3.eshop.model.cart.Cart;
import sk.stuba.fei.uim.oop.assignment3.eshop.service.cart.ICartService;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectBadRequestException;
import sk.stuba.fei.uim.oop.assignment3.utility.ObjectDoesNotExistException;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService service;
    @Autowired
    private CartFormator formator;

    @PostMapping()
    @ResponseBody
    public ResponseEntity<CartResponseBody> createCart() {
        Cart tmpCart = service.createCart();
        return new ResponseEntity<>(formator.toResponseBody(tmpCart), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<CartResponseBody> getCart(@PathVariable("id") Long id) {
        try {
            Cart tmpCart = service.getCart(id);
            return new ResponseEntity<>(formator.toResponseBody(tmpCart), HttpStatus.OK);
        } catch (ObjectDoesNotExistException objectDoesNotExistException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCart(@PathVariable("id") Long id) {
        try {
            service.deleteCart(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ObjectDoesNotExistException objectDoesNotExistException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/add")
    @ResponseBody
    public ResponseEntity<CartResponseBody> addProductToCart(@PathVariable Long id,
                                                             @RequestBody ProductInCartRequestBody productInCartRequestBody) {
        try {
            Cart tmpCart = service.addProductToCart(id, productInCartRequestBody);
            return new ResponseEntity<>(formator.toResponseBody(tmpCart), HttpStatus.OK);
        } catch (ObjectBadRequestException objectBadRequestException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ObjectDoesNotExistException objectDoesNotExistException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/pay")
    @ResponseBody
    public ResponseEntity<String> payForCart(@PathVariable Long id) {
        try {
            Double sum = service.getCartPrice(id);
            return new ResponseEntity<>(sum.toString(), HttpStatus.OK);
        } catch (ObjectBadRequestException objectBadRequestException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ObjectDoesNotExistException objectDoesNotExistException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
