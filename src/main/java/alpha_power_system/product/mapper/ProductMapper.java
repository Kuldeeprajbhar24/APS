package alpha_power_system.product.mapper;

import alpha_power_system.product.dto.response.ProductResponse;
import alpha_power_system.product.entity.Product;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductResponse toResponse(
            Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .model(product.getModel())
                .category(product.getCategory())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .warranty(product.getWarranty())
                .active(product.getActive())
                .build();
    }
}