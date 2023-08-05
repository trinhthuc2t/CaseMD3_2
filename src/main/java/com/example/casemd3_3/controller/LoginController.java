package com.example.casemd3_3.controller;


import com.example.casemd3_3.dao.DAO;
import com.example.casemd3_3.entity.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            login(req, resp);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ClassNotFoundException {
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");
        DAO dao = new DAO();
        Account account = dao.login(user, pass);
        if (account == null){
            req.setAttribute("mess", "Sai tên tài khoản hoặc mật khẩu");
            RequestDispatcher dispatcher = req.getRequestDispatcher("Login.jsp");
            dispatcher.forward(req, resp);
        } else {
            HttpSession session = req.getSession(); // thêm khai báo đối tượng session để lấy setsion
            session.setAttribute("acc", account); // gán session vào biến acc trên jsp
//            session.setMaxInactiveInterval(10); // set session trong vòng 10s
//            RequestDispatcher dispatcher = req.getRequestDispatcher("home"); // chuyển trang có dữ liệu đi theo
//            dispatcher.forward(req, resp);
            resp.sendRedirect("home"); // chuyển trang không dữ liệu đi theo
        }
    }
}
