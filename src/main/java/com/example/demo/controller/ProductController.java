package com.example.demo.controller;

import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.login.session.SessionConst;
import com.example.demo.domain.product.Product;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/com.solponge")
@RequiredArgsConstructor // 초기화 되지 않게 알아서 실행되는 녀석
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
    public String productsList(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
                               Model model,
                               @RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "20") int size) {
        model.addAttribute("member", loginMember);
        model.addAttribute("currentPage", page); // currentPage를 모델에 추가합니다.

        Page<Product> paginatedProducts = productService.findAllProductsByPage(page, size);
        model.addAttribute("paginatedProducts", paginatedProducts);

        return "product/productlist";
    }

    /*@GetMapping("/productList/search")
    public String produtsearchlist(@SessionAttribute(name = SessionConst.LOGIN_MEMBER,required = false) Member loginMember,
                                   Model model, HttpServletRequest request,
                                   @RequestParam("SearchSelect") String SearchSelec,
                                   @RequestParam("SearchValue") String SearchValue){
        model.addAttribute("member",loginMember);

        List<Product> data = productService.produtsearchlist(SearchSelec, SearchValue);

        String url = request.getQueryString();
        new pageing(20, request, data, model,"paginatedProducts", url);

        return "product/productlist";

    }*/

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


