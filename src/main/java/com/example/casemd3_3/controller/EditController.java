package com.example.casemd3_3.controller;


import com.example.casemd3_3.dao.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditController", value = "/edit")
public class EditController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String pid = req.getParameter("id");
        String pname = req.getParameter("name");
        String pimage = req.getParameter("image");
        String pprice = req.getParameter("price");
        String ptitle = req.getParameter("title");
        String pdescription = req.getParameter("description");
        String pcategory = req.getParameter("category");

        DAO dao = new DAO();
        dao.updateProductById(pname, pimage, pprice, ptitle, pdescription, pcategory, pid);
        resp.sendRedirect("managerProduct");
    }
}
