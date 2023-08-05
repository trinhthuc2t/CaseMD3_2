package com.example.casemd3_3.controller;

import com.example.casemd3_3.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DeleteProductController", value = "/delete-product")
public class DeleteProductController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int index = Integer.parseInt(request.getParameter("index"));

        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");

        if (cart != null && index >= 0 && index < cart.size()) {
            cart.remove(index);
            double totalPrice = 0;
            for (Product product : cart){
                totalPrice += product.getPrice();
            }
            double vat = totalPrice * 0.1; // vát 10% nhé;
            double totalPayment = totalPrice + vat;

            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("vat", vat);
            request.setAttribute("totalPayment", totalPayment);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cart.jsp");
        dispatcher.forward(request, response);
//        response.sendRedirect("Cart.jsp");
    }
}
