package sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.response_body;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductResponseBody {
    private Long id;
    private String name;
    private String description;
    private Long amount;
    private String unit;
    private Double price;
}
