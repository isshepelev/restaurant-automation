package ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date timestamp;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    private BigDecimal totalPrice;
}
