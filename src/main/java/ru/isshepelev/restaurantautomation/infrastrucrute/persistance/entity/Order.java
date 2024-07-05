package ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order{
    @Id
    private UUID id;

    private Date timestamp;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private BigDecimal totalPrice;
    private Status orderStatus;
    private String individualCode;
}