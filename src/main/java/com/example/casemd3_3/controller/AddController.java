package com.example.casemd3_3.controller;


import com.example.casemd3_3.dao.DAO;
import com.example.casemd3_3.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddController", value = "/add")
public class AddController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String pname = req.getParameter("name");
        String pimage = req.getParameter("image");
        String pprice = req.getParameter("price");
        String ptitle = req.getParameter("title");
        String pdescription = req.getParameter("description");
        String pcategory = req.getParameter("category");
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("acc");
        int sid = account.getId();

        DAO dao = new DAO();
        try {
            dao.insertProduct(pname, pimage, pprice, ptitle, pdescription, pcategory, sid);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("managerProduct");
    }
}
