package com.example.demo.domain.cart;


import com.example.demo.domain.member.Member;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartNum; // 카트 번호
    @OneToOne
    @JoinColumn(name = "member_num")
    private Member member; // 카트 소유자
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartItem> cartItems = new ArrayList<>(); // 카트 아이템 리스트
    private String cartDate;

   // private Long cartItemNumCounter = 1L;// 카트 아이템 번호 시퀀스

    public Cart(Member member) {
        this.member = member;
    }

    public Cart() {
    }

    public Cart(Long cart_num, Member member) {
        this.cartNum = cart_num;
        this.member = member;
    }

  /*  public void addCartItem(CartItem cartItem) throws RuntimeException {
        int orderStock = cartItem.getCartItemStock();
        int cartItemNum = Math.toIntExact(cartItem.getCartItemNum());

        if (cartItem.contains(cartItem)) { // 같은 상품이 이미 장바구니에 있을 경우
            CartItem existingCartItem = cartItem.get(cartItem.indexOf(cartItem));
            int stock = existingCartItem.getProduct().getProductStock(); // 기존 재고량
            int existingStock = existingCartItem.getCartItemStock(); // 기존 주문량
            if (stock < orderStock + existingStock) { // 재고보다 주문량 합이 많을 경우 예외 발생
                throw new RuntimeException("not enough stock");
            }
            existingCartItem.setCartItemStock(existingStock + orderStock); // 기존 상품 주문량 증가
        } else { // 새로운 상품을 장바구니에 추가
            int stock = cartItem.getProduct().getProductStock(); // 재고량
            if (stock < orderStock) { // 재고보다 주문량이 많을 경우 예외 발생
                throw new RuntimeException("not enough stock");
            }
            cartItem.add(cartItem); // 카트에 새로운 상품을 추가
        }
    }
*/


    /*카트에서 상품 제거*/
    public void removeCartItem(Long productNum) {
        CartItem itemToRemove = null;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getCartItemNum().equals(productNum)) { // 카트에서 상품 제거
                itemToRemove = cartItem;
                break;
            }
        }
        if (itemToRemove != null) {
            cartItems.remove(itemToRemove);
        }
    }


    /**
     * 조회로직
     */
    /*전체 주문 가격 조회*/
    public int getTotalPrice() {
        int totalPrice = 0;
        for (CartItem cartItem : this.cartItems) {
            if (cartItem.getCartItemStock() > 0) {
                totalPrice += cartItem.getTotalPrice();
            }
        }
        return totalPrice;
    }

    // 연관 관계 편의 메소드
    public void setMember(Member member) {
        this.member = member;
        if (member.getCart() != this) {
            member.setCart(this);
        }
    }

}
