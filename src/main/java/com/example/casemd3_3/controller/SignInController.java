package com.example.casemd3_3.controller;


import com.example.casemd3_3.dao.DAO;
import com.example.casemd3_3.entity.Account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SignInController", value = "/signup")
public class SignInController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            signin(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void signin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        String repass = req.getParameter("repass");
        if (!pass.equals(repass)){
            resp.sendRedirect("Login.jsp");
        } else {
            DAO dao = new DAO();
            Account account = dao.checkAccountExist(user);
            if (account == null){
                dao.signup(user, pass);
                resp.sendRedirect("home");
            } else {
                resp.sendRedirect("Login.jsp");
            }
        }
    }
}
