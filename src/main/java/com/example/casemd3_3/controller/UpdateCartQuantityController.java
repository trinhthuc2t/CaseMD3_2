package com.example.casemd3_3.controller;


import com.example.casemd3_3.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UpdateCartQuantityController", value = "/update-cart-quantity")
public class UpdateCartQuantityController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String indexStr = request.getParameter("index");
        String changeStr = request.getParameter("change");

        int index = Integer.parseInt(indexStr);
        int change = Integer.parseInt(changeStr);

        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        if (cart != null && index >= 0 && index < cart.size()) {
            Product product = cart.get(index);
            int newQuantity = product.getQuantity() + change;

            if (newQuantity > 1) {
                product.setQuantity(newQuantity);
            } else {
                cart.remove(index);
            }
        }

        double totalPrice = 0;
        for (Product product : cart){
            totalPrice += product.getPrice() * product.getQuantity();
        }
        double vat = totalPrice * 0.1; // vát 10%
        double totalPayment = totalPrice + vat; // tổng tiền thanh toán

        session.setAttribute("totalPrice", totalPrice);
        session.setAttribute("vat", vat);
        session.setAttribute("totalPayment", totalPayment);
        // Cập nhật giá trị khác trong session (totalPrice, vat, totalPayment) (nếu cần)

        response.setStatus(HttpServletResponse.SC_OK);
    }
}
