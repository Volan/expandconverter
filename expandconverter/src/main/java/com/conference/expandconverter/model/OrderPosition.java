package com.conference.expandconverter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Table(name = "order_positions")
@NoArgsConstructor
@AllArgsConstructor
public class OrderPosition {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    private Long orderId;

    private Long count;

    public OrderPosition(Item item, Long orderId, Long count) {
        this.item = item;
        this.orderId = orderId;
        this.count = count;
    }

}
