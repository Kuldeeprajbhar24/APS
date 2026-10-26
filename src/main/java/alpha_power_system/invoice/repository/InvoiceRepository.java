package alpha_power_system.invoice.repository;

import alpha_power_system.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {

    boolean existsByInvoiceNumber(String invoiceNumber);
}