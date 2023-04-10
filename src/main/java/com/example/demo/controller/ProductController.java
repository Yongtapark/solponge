package com.example.demo.controller;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.domain.product.Product;
import com.example.demo.repository.product.ProductSearchCond;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/com.solponge")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;


   /* @GetMapping("/productList")
    public String produtslist(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,
                              Model model, HttpServletRequest request){
        model.addAttribute("member",loginMember);

        List<Product> data = productService.getproductList();
        new pageing(20, request, data, model, "paginatedProducts");


        return "product/productlist";
    }*/

    @GetMapping("/productList")
    public String productList(Model model,
                              @PageableDefault(page = 0, size = 20, sort ="productNum",direction = Sort.Direction.ASC) Pageable pageable,
                              String searchKeyword) {
        Page<Product> paginatedProducts = null;
        if (searchKeyword==null){
            paginatedProducts = productService.findAll(pageable);
        }else {
            paginatedProducts = productService.productSearchList(searchKeyword,pageable);
        }

        int nowPage= paginatedProducts.getPageable().getPageNumber()+1 ;
        int totalPages = paginatedProducts.getTotalPages();
        int startPage = Math.max(nowPage - 5, 1);
        int endPage = Math.min(startPage + 9, totalPages);

        if (endPage - startPage < 9 && totalPages > 9) {
            startPage = Math.max(endPage - 9, 1);
        }

        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("totalPages",totalPages);

        model.addAttribute("paginatedProducts", paginatedProducts);
        return "product/productList";
    }




    @GetMapping("/product/{productId}")
    public String produtpage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,
                             @PathVariable int productId, Model model){
        model.addAttribute("member",loginMember);
        Product vo = productService.getProduct(productId);
        System.out.println(productId);
        model.addAttribute("productVo", vo);
        return "product/productpage";
    }

    @PostMapping("/product/{productId}")
    public String produtprocess(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,
                                @PathVariable int productId, Model model,
                                @RequestParam Map<String,String> requestParams,
                                @RequestParam("quantityinput") String quantityinput){
        System.out.println("productId,"+productId);
        System.out.println("quantityinput,"+quantityinput);
        boolean check = requestParams.containsKey("button1");
        model.addAttribute("product_num",productId);
        model.addAttribute("quantityinput",quantityinput);
        model.addAttribute("member",loginMember);
        model.addAttribute("check",check);
        if (check) {
            return "redirect:/com.solponge/member/"+loginMember.getMemberNo()+"/"+ productId + "/" + quantityinput + "/true";
        } else {
            return "redirect:/com.solponge/member/"+loginMember.getMemberNo()+"/myPage/cart/"+ productId + "/" + quantityinput;
        }

    }



}


