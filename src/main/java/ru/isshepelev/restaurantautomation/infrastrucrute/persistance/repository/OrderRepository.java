package ru.isshepelev.restaurantautomation.infrastrucrute.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.isshepelev.restaurantautomation.infrastrucrute.persistance.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
