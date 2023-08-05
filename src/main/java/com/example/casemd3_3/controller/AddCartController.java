package com.example.casemd3_3.controller;



import com.example.casemd3_3.dao.DAO;
import com.example.casemd3_3.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddCartController", value = "/add-to-cart")
public class AddCartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            addCart(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCart(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String id = request.getParameter("id");
        DAO dao = new DAO();
        Product getProducByID = dao.getProductByID(id);

        HttpSession session = request.getSession();
        List<Product> cart = (List<Product>) session.getAttribute("cart");
        if (cart == null){
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        boolean productExists = false;
        for (Product p : cart) {
            if (p.getId() == (getProducByID.getId())) {
                p.setQuantity(p.getQuantity() + 1);
                productExists = true;
                break;
            }
        }
        if (!productExists) {
            getProducByID.setQuantity(1);
            cart.add(getProducByID);
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
        response.sendRedirect("Cart.jsp");
    }
}

