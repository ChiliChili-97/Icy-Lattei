package com.sparta.project.icylattei.order.service;

import com.sparta.project.icylattei.cart.dto.CartResponseDto;
import com.sparta.project.icylattei.cart.entity.Cart;
import com.sparta.project.icylattei.order.dto.OrderRequestDto;
import com.sparta.project.icylattei.order.dto.OrderResponseDto;
import com.sparta.project.icylattei.order.dto.OrderUpdateRequestDto;
import com.sparta.project.icylattei.order.dto.OrdersResponseDto;
import com.sparta.project.icylattei.order.entity.Order;
import com.sparta.project.icylattei.order.repository.OrderRepository;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    public OrderResponseDto createOrder(OrderRequestDto requestDto) {
        Order order = orderRepository.save(new Order(requestDto));
        return new OrderResponseDto(order);
    }

    public OrdersResponseDto getOrders() {
        List<Order> orders = orderRepository.findAll();
        return new OrdersResponseDto(orders);
    }

    public OrderResponseDto updateOrder(Long orderId, User user, OrderRequestDto requestDto) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("찾을 수 없습니다."));
        order.update(requestDto);
        return new OrderResponseDto(order);
    }

    public OrderResponseDto deleteOrder(Long orderId, User user) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("장바구니를 찾을 수 없습니다."));
        orderRepository.delete(order);
        return new OrderResponseDto(order);
    }
}
