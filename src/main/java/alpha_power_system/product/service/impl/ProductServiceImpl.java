package alpha_power_system.product.service.impl;

import alpha_power_system.exception.DuplicateResourceException;
import alpha_power_system.exception.ResourceNotFoundException;
import alpha_power_system.product.dto.request.CreateProductRequest;
import alpha_power_system.product.dto.request.UpdateProductRequest;
import alpha_power_system.product.dto.response.ProductResponse;
import alpha_power_system.product.entity.Product;
import alpha_power_system.product.mapper.ProductMapper;
import alpha_power_system.product.repository.ProductRepository;
import alpha_power_system.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl
        implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(
            CreateProductRequest request) {

        if (productRepository.existsByName(
                request.getName())) {

            throw new DuplicateResourceException(
                    "Product already exists");
        }

        Product product = Product.builder()
                .name(request.getName())
                .brand(request.getBrand())
                .model(request.getModel())
                .category(request.getCategory())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuantity(
                        request.getStockQuantity())
                .warranty(request.getWarranty())
                .active(true)
                .build();

        return ProductMapper.toResponse(
                productRepository.save(product));
    }

    @Override
    public List<ProductResponse> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(
            UUID id) {

        Product product =
                productRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Product not found"));

        return ProductMapper.toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(
            UUID id,
            UpdateProductRequest request) {

        Product product = productRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found"));

        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setModel(request.getModel());
        product.setCategory(request.getCategory());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(
                request.getStockQuantity());
        product.setWarranty(
                request.getWarranty());
        product.setActive(
                request.getActive());

        return ProductMapper.toResponse(
                productRepository.save(product));
    }

    @Override
    public void deleteProduct(UUID id) {

        Product product = productRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found"));

        productRepository.delete(product);
    }
}