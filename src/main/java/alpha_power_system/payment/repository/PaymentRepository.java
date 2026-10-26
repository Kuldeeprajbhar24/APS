package alpha_power_system.payment.repository;

import alpha_power_system.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    boolean existsByPaymentNumber(String paymentNumber);

    List<Payment> findByInvoiceId(UUID invoiceId);
}