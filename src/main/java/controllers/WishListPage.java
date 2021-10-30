package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.UserModel;

@WebServlet(urlPatterns = "/wishlist")
public class WishListPage extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
    req.setCharacterEncoding("UTF-8");
    System.out.println("Hello World");

    HttpSession session = req.getSession();

    UserModel user = (UserModel) session.getAttribute("user");

    if (user == null) {
      resp.sendRedirect("home");
      System.out.println("out");
      return;
    }

    req.setAttribute("user", user);

    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + "WishListPage.jsp");
    rd.forward(req, resp);
  }
}