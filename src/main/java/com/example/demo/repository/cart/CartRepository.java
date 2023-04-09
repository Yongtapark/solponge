package com.example.demo.repository.cart;

import com.example.demo.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart,Long> {
}
