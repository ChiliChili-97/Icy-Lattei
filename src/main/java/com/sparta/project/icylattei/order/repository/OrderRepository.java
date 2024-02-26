package com.sparta.project.icylattei.order.repository;

import com.sparta.project.icylattei.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
