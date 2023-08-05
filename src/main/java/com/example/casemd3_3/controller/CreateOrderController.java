package com.example.casemd3_3.controller;


import com.example.casemd3_3.dao.DAO;
import com.example.casemd3_3.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateOrderController", value = "/create-order")
public class CreateOrderController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            createOrder(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String totalPrice = req.getParameter("totalPrice");
        String vat = req.getParameter("vat");
        String totalPayment = req.getParameter("totalPayment");

        List<Product> cart = (List<Product>) req.getSession().getAttribute("cart");

        DAO dao = new DAO();
        dao.placeOrder(name, phone, totalPrice, vat, totalPayment, cart);
        req.getSession().removeAttribute("cart");
        req.getSession().removeAttribute("totalPrice");
        req.getSession().removeAttribute("vat");
        req.getSession().removeAttribute("totalPayment");
        resp.sendRedirect("OrderConfirmation.jsp");
//        dao.order(name, phone, totalPrice, vat, totalPayment);


//        HttpSession session = req.getSession();
//        session.removeAttribute("cart");
//
//
//        resp.sendRedirect("OrderConfirmation.jsp");
    }
}
