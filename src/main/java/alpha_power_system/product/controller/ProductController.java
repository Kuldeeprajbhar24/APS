package alpha_power_system.product.controller;

import alpha_power_system.common.response.ApiResponse;
import alpha_power_system.product.dto.request.CreateProductRequest;
import alpha_power_system.product.dto.request.UpdateProductRequest;
import alpha_power_system.product.dto.response.ProductResponse;
import alpha_power_system.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(
            @Valid
            @RequestBody
            CreateProductRequest request) {

        return ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("Product created successfully")
                .data(productService.createProduct(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ProductResponse>>
    getAllProducts() {

        return ApiResponse
                .<List<ProductResponse>>builder()
                .success(true)
                .message("Products fetched successfully")
                .data(productService.getAllProducts())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse>
    getProductById(
            @PathVariable UUID id) {

        return ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("Product fetched successfully")
                .data(productService.getProductById(id))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductResponse>
    updateProduct(
            @PathVariable UUID id,
            @Valid
            @RequestBody
            UpdateProductRequest request) {

        return ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("Product updated successfully")
                .data(
                        productService.updateProduct(
                                id,
                                request
                        )
                )
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String>
    deleteProduct(
            @PathVariable UUID id) {

        productService.deleteProduct(id);

        return ApiResponse.<String>builder()
                .success(true)
                .message("Product deleted successfully")
                .data("Deleted")
                .build();
    }
}