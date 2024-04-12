package com.conference.expandconverter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Table(name = "delivery_types")
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String type;

    public DeliveryType(String type) {
        this.type = type;
    }

}
