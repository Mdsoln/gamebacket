package com.gamebacket.vercel.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "status")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderStatus {
    @Id
    @SequenceGenerator(
            name = "orderStatus_sequence",
            sequenceName = "orderStatus_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderStatus_sequence"
    )
    private Long status_id;

    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id",referencedColumnName = "orderId")
    private Order order;

    private String order_status;

    private LocalDate date_created;

    private LocalDate date_updated;

    @PrePersist
    public void onCreate(){
        date_created = LocalDate.now();
    }

    @PreUpdate
    public void onUpdate(){
        date_updated = LocalDate.now();
    }

}
