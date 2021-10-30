package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;

@WebServlet(urlPatterns = "/signup")
public class SignUpPage extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
    req.setCharacterEncoding("UTF-8");
    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + "SignUpPage.jsp");
    rd.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
    req.setCharacterEncoding("UTF-8");
    ServletContext sc = getServletContext();
    String url = "";

    String fullname = req.getParameter("fullname");
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    UserDAO userDao = new UserDAO();

    try {
      userDao.register(fullname, username, password);

      System.out.println("in");
      resp.sendRedirect("login");
      System.out.println("out");
      return;
    } catch (Exception err) {
      System.out.println(err);
      url = "/WEB-INF/views/" + "SignUpPage.jsp";
      req.setAttribute("message", err.getMessage());
    }

    sc.getRequestDispatcher(url).forward(req, resp);
  }
}
