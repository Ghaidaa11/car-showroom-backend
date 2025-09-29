package com.challenge.car_showroom.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "car_showrooms")
@SQLDelete(sql = "UPDATE car_showrooms SET is_deleted = 1 WHERE id = ?")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarShowroom extends BaseProperties{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String commercialRegistrationNumber;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    private String contactNumber;

    private String address;

    private boolean isDeleted = false;

}
