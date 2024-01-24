package com.gamebacket.vercel.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderItem {
    @Id
    @SequenceGenerator(
            name = "orderItem_sequence",
            sequenceName = "orderItem_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderItem_sequence"
    )
    private Long orderItemId;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "game_id",referencedColumnName = "id")
    private Games game;

    @ManyToOne
    @JoinColumn(name = "accessory_id",referencedColumnName = "id")
    private Accessories accessory;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
