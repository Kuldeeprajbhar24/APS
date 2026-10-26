package alpha_power_system.product.repository;

import alpha_power_system.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    boolean existsByName(String name);

}