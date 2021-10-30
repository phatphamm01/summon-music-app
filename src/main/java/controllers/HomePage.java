package controllers;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAO.UserDAO;
import models.UserModel;

@WebServlet(urlPatterns = "/home")
public class HomePage extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
    req.setCharacterEncoding("UTF-8");
    System.out.println("Hello World");

    HttpSession session = req.getSession();

    UserModel user = (UserModel) session.getAttribute("user");

    if (user != null) {

      UserDAO userDao = new UserDAO();

      Gson gson = new Gson();
      Cookie cookieWishList = new Cookie("wishlist",
          URLEncoder.encode(gson.toJson(userDao.getWishList(user.getId())), "UTF-8"));
      resp.addCookie(cookieWishList);

      req.setAttribute("user", user);
    }

    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/" + "HomePage.jsp");
    rd.forward(req, resp);
  }
}
