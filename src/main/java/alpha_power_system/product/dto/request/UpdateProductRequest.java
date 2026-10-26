package alpha_power_system.product.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    private String model;

    @NotBlank
    private String category;

    private String description;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @NotNull
    @Min(0)
    private Integer stockQuantity;

    private String warranty;

    private Boolean active;
}