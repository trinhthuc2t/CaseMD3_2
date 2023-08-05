package com.example.casemd3_3.controller;


import com.example.casemd3_3.dao.DAO;
import com.example.casemd3_3.entity.Account;
import com.example.casemd3_3.entity.Category;
import com.example.casemd3_3.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ManagerController", value = "/managerProduct")
public class ManagerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            loadAllProductBySellUser(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAllProductBySellUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("acc");
        int id = account.getId();
        DAO dao = new DAO();
        List<Product> list = dao.getProductBySellID(id);
        List<Category> listCategory = dao.getAllCategory();
        request.setAttribute("listP", list);
        request.setAttribute("listC", listCategory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ManagerProduct.jsp");
        dispatcher.forward(request, response);
    }
}