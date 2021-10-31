package controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAO.UserDAO;
import config.UTF8;
import models.UserModel;

@WebServlet(urlPatterns = "/login")
public class LoginPage extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UTF8.set(req, resp);

    req.getRequestDispatcher("/WEB-INF/views/" + "LoginPage.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UTF8.set(req, resp);

    String url = "";
    boolean checkLogin = this.handleLogin(req, resp);

    if (!checkLogin) {
      System.out.println("fail");
      url = "/WEB-INF/views/" + "LoginPage.jsp";
      req.getRequestDispatcher(url).forward(req, resp);
    }
  }

  private boolean handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    HttpSession session = req.getSession();

    UserDAO userDao = new UserDAO();

    try {
      UserModel user = userDao.login(username, password);
      session.setAttribute("user", user);

      Gson gson = new Gson();
      Cookie cookieWishList = new Cookie("wishlist", URLEncoder.encode(gson.toJson(user.getWishList()), "UTF-8"));
      resp.addCookie(cookieWishList);

      resp.sendRedirect("home");
      return true;
    } catch (Exception err) {
      req.setAttribute("message", err.getMessage());
    }

    return false;
  }
}
