package com.example.demo.controller;

import com.example.demo.domain.cart.Cart;
import com.example.demo.domain.cart.CartItem;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.domain.product.Product;
import com.example.demo.service.interfaces.CartService;
import com.example.demo.service.interfaces.MemberService;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/com.solponge/member/{MEMBER_NUM}/myPage/cart")
public class CartController {
    private final MemberService memberService;
    private final ProductService productService;
    private final CartService cartService;

    /**
     * 장바구니
     */
    @GetMapping
    public String getCart(Model model,
                       HttpServletRequest request){
        //세션을 받아 계정객체 받아옴
        Member member = getLoginMember(request);
        log.info("===getCart====");
        log.info("member={}",member.getMemberNum());
        //---------------------------------------------------------

        //회원의 카트 조회
        Cart myCart = cartService.getMyCart(member.getMemberNum());


        //cartItem 을 넣은 cart 를 model 에 저장
        model.addAttribute("cart",myCart);
        log.info("myCart={}",myCart);
        return "member/cart";
    }


    /* 장바구니 추가*/
    @GetMapping("/{productId}/{quantityinput}")
    public String addCartItem(@PathVariable int productId,
                           @PathVariable int quantityinput,
                           HttpServletRequest request){

        Member loginMember = getLoginMember(request);
        //받아온 상품번호로 상품객체 소환
        Product getproduct = productService.findByNo((long) productId).get();
        //cartItem 객체를 생성하여 필요한 값을 cartItemVo로 전달
        CartItem insertingCartItem = new CartItem(loginMember,getproduct, quantityinput);
        //받아온 상품정보를 CART_ITEM 에 저장
        CartItem insertedCartItem = cartService.addItem(insertingCartItem);
        log.info("장바구니에 추가된 상품정보={}",cartService.findItem(insertedCartItem.getCartItemNum()));

        return "redirect:/com.solponge/product/"+productId;
    }



    /* 장바구니 아이템 삭제*/
    @PostMapping("/deleteCartItem")
    public String cartItemDelete(@RequestParam("cartItemNum") Long cartItemNum,
                                 HttpServletRequest request){
        Member loginMember = getLoginMember(request);
        log.info("delete.loginMember={}",loginMember);
        log.info("cartItem_num={}",cartItemNum);
        CartItem item = cartService.findItem((long) cartItemNum);
        cartService.deleteItem(item);

        return "redirect:/com.solponge/member/" + loginMember.getMemberNum() + "/myPage/cart";
    }

    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (Member) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }
    
   
}
