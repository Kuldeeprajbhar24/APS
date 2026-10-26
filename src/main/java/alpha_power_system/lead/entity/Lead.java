package alpha_power_system.lead.entity;

import alpha_power_system.common.entity.BaseEntity;
import alpha_power_system.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "leads")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lead extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",
            nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String productInterest;

    @Column(length = 1000)
    private String remarks;

    @Enumerated(EnumType.STRING)
    private LeadStatus status;
}