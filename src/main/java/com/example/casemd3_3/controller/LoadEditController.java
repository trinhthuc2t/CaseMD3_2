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

@WebServlet(name = "LoadEditController", value = "/loadProductEdit")
public class LoadEditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            detail(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void detail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        String id = req.getParameter("ProducID");
        DAO dao = new DAO();
        Product getProducByID = dao.getProductByID(id);
        List<Category> listCategory = dao.getAllCategory();
        req.setAttribute("detail", getProducByID);
        req.setAttribute("listC", listCategory);
        RequestDispatcher dispatcher = req.getRequestDispatcher("Edit.jsp");
        dispatcher.forward(req, resp);
    }
}
