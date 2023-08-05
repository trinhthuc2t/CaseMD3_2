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

@WebServlet(name = "SearchController", value = "/search")
public class SearchController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            search(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        req.setCharacterEncoding("UTF-8"); // set tiếng việt phục vụ cho việc tìm kiếm bằng tiềng việ, mặc định đang là tiếng anh
        String txtSearch = req.getParameter("txt");
        DAO dao = new DAO();
        List<Product> list = dao.searchProductByName(txtSearch);
        List<Category> listCategory = dao.getAllCategory();
        Product lastProduct = dao.getLastProduct();
        req.setAttribute("ListProduct", list);
        req.setAttribute("ListCategory", listCategory);
        req.setAttribute("LastProduct", lastProduct);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Home.jsp");
        dispatcher.forward(req, resp);
    }
}
