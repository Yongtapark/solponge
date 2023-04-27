package com.example.demo.controller;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.domain.product.Product;
import com.example.demo.utils.SearchCond;
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

import java.util.Map;


@Controller
@RequestMapping("/com.solponge")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;



    @GetMapping("/productList")
    public String productList(Model model,
                              @PageableDefault(page = 0, size = 20, sort ="productNum",direction = Sort.Direction.ASC) Pageable pageable,
                              String searchSelect, String searchValue) {
        Page<Product> paginatedProducts = null;
        if ((searchSelect==null||searchSelect=="")&&(searchValue==null||searchValue=="")){
            paginatedProducts = productService.findAll(pageable);
        }else {
            SearchCond cond = new SearchCond(searchSelect, searchValue);
            paginatedProducts = productService.search(cond, pageable);
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




    @GetMapping("/product/{productNum}")
    public String getProductPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,
                              @PathVariable Long productNum, Model model){
        model.addAttribute("member",loginMember);
        Product product = productService.getProduct(productNum);
        model.addAttribute("product", product);
        return "product/productPage";
    }

    @PostMapping("/product/{productNum}")
    public String postProductPage(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,
                                @PathVariable Long productNum, Model model,
                                @RequestParam Map<String,String> requestParams,
                                @RequestParam("quantityinput") String quantityinput){
        log.info("=====productId 진입=====");
        System.out.println("productId,"+ productNum);
        System.out.println("quantityinput,"+quantityinput);
        boolean check = requestParams.containsKey("button1");
        model.addAttribute("product_num", productNum);
        model.addAttribute("quantityinput",quantityinput);
        model.addAttribute("member",loginMember);
        model.addAttribute("check",check);
        if (check) {
            return "redirect:/com.solponge/member/"+loginMember.getMemberNum()+"/"+ productNum + "/" + quantityinput + "/true";
        } else {
            return "redirect:/com.solponge/member/"+loginMember.getMemberNum()+"/myPage/cart/"+ productNum + "/" + quantityinput;
        }

    }



}


