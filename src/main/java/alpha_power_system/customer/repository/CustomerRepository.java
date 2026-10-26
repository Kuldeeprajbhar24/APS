package alpha_power_system.customer.repository;

import alpha_power_system.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    boolean existsByMobile(String mobile);

    boolean existsByEmail(String email);
}