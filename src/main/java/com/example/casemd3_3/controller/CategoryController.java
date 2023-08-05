package com.example.casemd3_3.controller;


import com.example.casemd3_3.dao.DAO;
import com.example.casemd3_3.entity.Category;
import com.example.casemd3_3.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/category")
public class CategoryController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            getAllProductByCategory(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    protected void getAllProductByCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String categoryID = request.getParameter("categoryID");
        DAO dao = new DAO();
        List<Product> listProductByCategory = dao.getProductByCategoryID(categoryID);
        List<Category> listCategory = dao.getAllCategory();
        Product lastProduct = dao.getLastProduct();
        request.setAttribute("ListProduct", listProductByCategory);
        request.setAttribute("ListCategory", listCategory);
        request.setAttribute("LastProduct", lastProduct);
        request.setAttribute("tag", categoryID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
        dispatcher.forward(request, response);

    }
}
