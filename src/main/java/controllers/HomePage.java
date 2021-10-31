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

@WebServlet(urlPatterns = "/home")
public class HomePage extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UTF8.set(req, resp);

    this.getUser(req, resp);

    req.getRequestDispatcher("/WEB-INF/views/" + "HomePage.jsp").forward(req, resp);
  }

  private void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    UserModel user = (UserModel) session.getAttribute("user");

    if (user == null) {
      return;
    }

    UserDAO userDao = new UserDAO();
    Gson gson = new Gson();
    Cookie cookieWishList = new Cookie("wishlist",
        URLEncoder.encode(gson.toJson(userDao.getWishList(user.getId())), "UTF-8"));

    resp.addCookie(cookieWishList);
  }
}
