package com.sparta.project.icylattei.cart.service;

import com.sparta.project.icylattei.cart.dto.CartRequestDto;
import com.sparta.project.icylattei.cart.dto.CartResponseDto;
import com.sparta.project.icylattei.cart.dto.CartsResponseDto;
import com.sparta.project.icylattei.cart.entity.Cart;
import com.sparta.project.icylattei.cart.repository.CartRepository;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.repository.UserRepository;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;

    public CartResponseDto createCart(CartRequestDto requestDto, User user) {
        Cart cart = cartRepository.save(new Cart(requestDto, user));
        return new CartResponseDto(cart);
    }

    public CartsResponseDto getCart(User user) {
        List<Cart> cart = cartRepository.findAllByUserAndCartStatus(user, "장바구니");
        return new CartsResponseDto(cart);
    }

    public CartResponseDto deleteCart(Long cartId, User user) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("장바구니를 찾을 수 없습니다."));
        if(!cart.getUser().equals(user)){
            throw new IllegalArgumentException("본인의 장바구니가 아닙니다.");
        }
        cartRepository.delete(cart);
        return new CartResponseDto(cart);
    }

    public CartResponseDto updateCart(Long cartId, CartRequestDto requestDto, User user) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException("장바구니를 찾을 수 없습니다."));
        if(!cart.getUser().equals(user)){
            throw new IllegalArgumentException("본인의 장바구니가 아닙니다.");
        }
        cart.update(requestDto);
        return new CartResponseDto(cart);
    }
}
