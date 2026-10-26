package alpha_power_system.payment.dto.response;

import alpha_power_system.payment.entity.PaymentMethod;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Builder
public class PaymentResponse {

    private UUID id;

    private String paymentNumber;

    private UUID invoiceId;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private LocalDate paymentDate;

    private String transactionReference;

    private String remarks;
}