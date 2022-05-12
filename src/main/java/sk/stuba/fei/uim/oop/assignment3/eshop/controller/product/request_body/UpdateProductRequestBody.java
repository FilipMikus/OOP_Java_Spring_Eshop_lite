package sk.stuba.fei.uim.oop.assignment3.eshop.controller.product.request_body;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProductRequestBody {
    private String name;
    private String description;

    public Boolean isNameNull() {
        return name == null;
    }

    public Boolean isDescriptionNull() {
        return description == null;
    }
}
