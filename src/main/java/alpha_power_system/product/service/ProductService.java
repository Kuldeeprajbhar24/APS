package alpha_power_system.product.service;

import alpha_power_system.product.dto.request.CreateProductRequest;
import alpha_power_system.product.dto.request.UpdateProductRequest;
import alpha_power_system.product.dto.response.ProductResponse;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponse createProduct(
            CreateProductRequest request);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(UUID id);

    ProductResponse updateProduct(
            UUID id,
            UpdateProductRequest request);

    void deleteProduct(UUID id);
}