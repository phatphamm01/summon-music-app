package controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import DAO.UserDAO;
import models.UserModel;
import models.WishListModel;

@WebServlet(urlPatterns = "/login")
public class LoginPage extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
    req.setCharacterEncoding("UTF-8");

    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + "LoginPage.jsp");
    rd.forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
    req.setCharacterEncoding("UTF-8");
    HttpSession session = req.getSession();
    ServletContext sc = getServletContext();
    String url = "";

    String username = req.getParameter("username");
    String password = req.getParameter("password");

    UserDAO userDao = new UserDAO();

    try {
      url = "/WEB-INF/views/" + "LoginPage.jsp";
      UserModel user = userDao.login(username, password);
      session.setAttribute("user", user);

      Gson gson = new Gson();

      Cookie cookieWishList = new Cookie("wishlist", URLEncoder.encode(gson.toJson(user.getWishList()), "UTF-8"));

      System.out.println(cookieWishList);

      resp.addCookie(cookieWishList);

      resp.sendRedirect("home");
      return;
    } catch (Exception err) {
      System.out.println(err);
      url = "/WEB-INF/views/" + "LoginPage.jsp";
      req.setAttribute("message", err.getMessage());
    }

    sc.getRequestDispatcher(url).forward(req, resp);
  }
}
