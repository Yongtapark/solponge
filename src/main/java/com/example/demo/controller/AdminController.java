package com.example.demo.controller;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.domain.payment.Payment;
import com.example.demo.domain.product.Product;
import com.example.demo.domain.utils.SearchCond;
import com.example.demo.service.interfaces.CartService;
import com.example.demo.service.interfaces.MemberService;
import com.example.demo.service.interfaces.PaymentService;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/com.solponge/admin")
public class AdminController {
    private final MemberService memberService;
    private final CartService cartService;
    private final ProductService productService;
    private final PaymentService paymentService;

    /**
     * 회원관리
     */

    @GetMapping("/member")
    public String memberManage(Model model,
                               @PageableDefault(page = 0,size = 20, sort = "memberNum", direction = Sort.Direction.ASC) Pageable pageable,
                               String searchSelect, String searchValue,
                               HttpServletRequest request){
        getLoginMember(request);
        Page<Member> paginatedMember = null;
        if(searchSelect==null && searchValue==null){
            paginatedMember =memberService.findAll(pageable);
        }else {
            SearchCond cond = new SearchCond(searchSelect, searchValue);
            paginatedMember = memberService.search(cond, pageable);
        }

        int nowPage = paginatedMember.getPageable().getPageNumber() + 1;
        int totalPages = paginatedMember.getTotalPages();
        int startPage = Math.max(nowPage - 5, 1);
        int endPage = Math.min(startPage + 9, totalPages);

        if(endPage - startPage <9 && totalPages>9){
            startPage = Math.max(endPage - 9, 1);
        }
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("paginatedMember",paginatedMember);

        return "admin/memberManageList";
    }

    @GetMapping("/member/{memberNum}/update")
    public String editMember(@PathVariable Long memberNum, Model model, HttpServletRequest request) {
        getLoginMember(request);
        Member member = memberService.findByNo(memberNum).get();
        log.info("adminMember={}",member);
        model.addAttribute("member", member);
        return "admin/memberManagePage";
    }

    @PostMapping("/member/{memberNum}/update")
    public String edit(HttpServletRequest request,
                       @ModelAttribute Member member,
                       @PathVariable Long memberNum) {
        log.info("member={}",member.getMemberPhone());
        log.info("memberNum={}",memberNum);
        getLoginMember(request);

        memberService.update(memberNum,member);


        return "redirect:/com.solponge/admin/member";
    }

    @GetMapping("/member/{memberNum}/delete")
    public String deleteMember(@PathVariable Long memberNum,HttpServletRequest request) {
        getLoginMember(request);
        cartService.deleteCartByMemberNum(memberNum);
        memberService.delete(memberService.findByNo(memberNum).get().getMemberNum());
        return "redirect:/com.solponge/admin/member";
    }

    /**
     * 주문관리
     */
    @GetMapping("/order")
    public String order(Model model, HttpServletRequest request) {
        List<Payment> paymentList = paymentService.getPaymentList();
        model.addAttribute("paymentList",paymentList);
        return "admin/orderManageList";

    }


    /**
     * 상품관리
     */
    @GetMapping("/product") //수정완료
    public String product(Model model,
                          HttpServletRequest request,
                          @PageableDefault(page = 0,size = 10, sort = "productNum", direction = Sort.Direction.ASC) Pageable pageable,
                          String searchSelect, String searchValue) {
        getLoginMember(request);
        Page<Product> paginatedProduct = null;
        if(searchSelect==null && searchValue==null){
            paginatedProduct =productService.findAll(pageable);
        }else {
            SearchCond cond = new SearchCond(searchSelect, searchValue);
            paginatedProduct = productService.search(cond, pageable);
        }

        int nowPage = paginatedProduct.getPageable().getPageNumber() + 1;
        int totalPages = paginatedProduct.getTotalPages();
        int startPage = Math.max(nowPage - 5, 1);
        int endPage = Math.min(startPage + 9, totalPages);

        if(endPage - startPage <9 && totalPages>9){
            startPage = Math.max(endPage - 9, 1);
        }
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("paginatedProduct",paginatedProduct);
        return "admin/productManageList";
    }

    @GetMapping("/product/{productNum}")
    public String deProduct(@PathVariable Long productNum, Model model) {
        log.info("productNum={}",productNum);
        Product product = productService.findByNo(productNum).get();
        model.addAttribute("product", product);
        return "admin/productManagePage";
    }

    @GetMapping("/product/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        return "admin/productManageAdd";
    }




    /**
     * 메서드
     */
    private Member getLoginMember(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null ? (Member) session.getAttribute(SessionConst.LOGIN_MEMBER) : null;
    }
}
