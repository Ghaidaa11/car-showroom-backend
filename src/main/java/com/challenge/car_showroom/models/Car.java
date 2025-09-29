package com.challenge.car_showroom.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Car extends BaseProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false, unique = true)
    private String vin;

    @Column(length = 25, nullable = false)
    private String maker;

    @Column(length = 25, nullable = false)
    private String model;

    @Column(nullable = false)
    private Integer modelYear;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showroom_id", nullable = false)
    private CarShowroom showroom;

    public Car(final String vin,
               final String maker,
               final String model,
               final Integer modelYear,
               final BigDecimal price,
               final CarShowroom showroom) {
        this.vin = vin;
        this.maker = maker;
        this.model = model;
        this.modelYear = modelYear;
        this.price = price;
        this.showroom = showroom;
    }
}
