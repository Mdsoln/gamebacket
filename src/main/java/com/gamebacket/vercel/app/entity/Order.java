package com.gamebacket.vercel.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private OrderStatus orderStatus;

    @Column(name = "order_number",nullable = false)
    private String orderNo;

    private String address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

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
