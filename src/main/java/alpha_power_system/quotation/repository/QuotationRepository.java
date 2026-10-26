package alpha_power_system.quotation.repository;

import alpha_power_system.quotation.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuotationRepository extends JpaRepository<Quotation, UUID> {

    boolean existsByQuotationNumber(String quotationNumber);
}