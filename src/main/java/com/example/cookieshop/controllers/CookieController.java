package com.example.cookieshop.controllers;

import com.example.cookieshop.models.Basket;
import com.example.cookieshop.models.Cookie;
import com.example.cookieshop.repositories.CookieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CookieController {
    CookieRepository repo = new CookieRepository();

    @GetMapping("/")
    public String index(HttpSession session){
        return "index";
    }

    @GetMapping("/basket")
    public String basket(HttpSession session, Model model){
        Basket basket =(Basket) session.getAttribute("basket");
        model.addAttribute("basket",basket.getCookieList());
        model.addAttribute("sum",basket.getSum());

        return "basket";
    }

    @GetMapping("/shop")
    public String shop(HttpSession session, Model m){
        m.addAttribute("cookies",repo.getAllCookies());

        return "shop";
    }

    @GetMapping("/addToBasket")
    public String add(@RequestParam String id,HttpSession session){
        System.out.println(id);

        Cookie cookie = repo.getCookieById(Integer.parseInt(id));

        if(session.getAttribute("basket")!=null){
            Basket basket = (Basket) session.getAttribute("basket");
            basket.addToBasket(cookie);
        }
        else{
            session.setAttribute("basket", new Basket());
            Basket basket = (Basket) session.getAttribute("basket");
            basket.addToBasket(cookie);
        }
        System.out.println(session.getAttribute("basket"));
        return "redirect:/shop";
    }
}
