package com.sparta.project.icylattei.order.dto;

import com.sparta.project.icylattei.cart.entity.Cart;
import com.sparta.project.icylattei.order.entity.Order;
import com.sparta.project.icylattei.product.entity.Product;
import java.sql.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderResponseDto {
    private Long id; //주문번호
    private List<Cart> carts;
    private int totalPrice = 0;
    private Date orderDate;



    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.orderDate = order.getOrderDate();
        for(int i = 0; i < carts.size(); i++){
            this.totalPrice += carts.get(i).getQuantity() * carts.get(i).getProduct().getPrice();
        }
    }
}
