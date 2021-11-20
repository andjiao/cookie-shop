package com.example.cookieshop.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Basket {

    private List<Cookie> cookieList;

    public Basket() {
        cookieList = new ArrayList<>();
    }

    public void addToBasket(Cookie cookie){

        cookieList.add(cookie);
    }

    public List<Cookie> getCookieList() {
        return cookieList;
    }

    public double getSum(){
        int sum = 0;
        for(Cookie cookie: cookieList){
            sum+= cookie.getPrice();
        }
        return sum;
    }

    public void setCookieList(List<Cookie> cookieList) {
        this.cookieList = cookieList;
    }

    @Override
    public String toString(){
        String listOfString = cookieList.stream().map(Object::toString).collect(Collectors.joining(" , "));

        return listOfString;
    }
}
