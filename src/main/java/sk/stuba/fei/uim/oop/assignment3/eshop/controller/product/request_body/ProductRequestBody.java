package sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.request_body;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequestBody {
    private String name;
    private String description;
    private Long amount;
    private String unit;
    private Double price;
}
