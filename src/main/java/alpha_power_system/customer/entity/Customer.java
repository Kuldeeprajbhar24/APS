package alpha_power_system.customer.entity;

import alpha_power_system.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String mobile;

    @Column(unique = true)
    private String email;

    private String companyName;

    private String gstNumber;

    private String address;

    private String city;

    private String state;

    private String pincode;

    private Boolean active = true;
}