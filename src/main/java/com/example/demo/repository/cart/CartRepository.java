package com.example.demo.repository.cart;

import com.example.demo.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByMemberMemberNum(Long memberNum);

    void deleteByMemberMemberNum(Long memberNum);
}
