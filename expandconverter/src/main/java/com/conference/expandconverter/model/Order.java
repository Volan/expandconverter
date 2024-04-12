package com.conference.expandconverter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderId")
    private List<OrderPosition> positions;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_type_id", referencedColumnName = "id")
    private DeliveryType deliveryType;

    public Order(Person person, DeliveryType deliveryType) {
        this.person = person;
        this.deliveryType = deliveryType;
    }

}
