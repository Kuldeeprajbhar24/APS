package alpha_power_system.invoice.entity;

import alpha_power_system.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "invoices", uniqueConstraints = {@UniqueConstraint(
                                                name = "uk_invoice_number",
                                                columnNames = "invoice_number"
                )
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "invoice_number", nullable = false, unique = true, length = 50
    )
    private String invoiceNumber;

    @Column(name = "customer_id", nullable = false
    )
    private UUID customerId;

    @Column(name = "quotation_id")
    private UUID quotationId;

    @Column(nullable = false, precision = 15, scale = 2
    )
    private BigDecimal amount;

    @Column(name = "gst_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal gstAmount;

    @Column(precision = 15, scale = 2
    )
    private BigDecimal discount;

    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2
    )
    private BigDecimal totalAmount;

    @Column(name = "paid_amount", nullable = false, precision = 15, scale = 2
    )
    private BigDecimal paidAmount;

    @Column(name = "due_amount", nullable = false, precision = 15, scale = 2
    )
    private BigDecimal dueAmount;

    @Column(name = "invoice_date", nullable = false
    )
    private LocalDate invoiceDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InvoiceStatus status;

    @Column(length = 1000)
    private String remarks;
}