package alpha_power_system.quotation.entity;

import alpha_power_system.common.entity.BaseEntity;
import alpha_power_system.customer.entity.Customer;
import alpha_power_system.lead.entity.Lead;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "quotations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Quotation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String quotationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_id")
    private Lead lead;

    @Column(nullable = false)
    private BigDecimal amount;

    private BigDecimal gstAmount;

    private BigDecimal discount;

    @Column(nullable = false)
    private BigDecimal finalAmount;

    private LocalDate validTill;

    private String remarks;

    @Enumerated(EnumType.STRING)
    private QuotationStatus status;
}