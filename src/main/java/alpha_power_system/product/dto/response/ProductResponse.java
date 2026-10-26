package alpha_power_system.product.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class ProductResponse {

    private UUID id;
    private String name;
    private String brand;
    private String model;
    private String category;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String warranty;
    private Boolean active;
}