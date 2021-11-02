package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import DAO.UserDAO;
import model.UserModel;
import model.WishListModel;

@WebServlet(urlPatterns = "/home")
public class HomePage extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    UserModel user = (UserModel) session.getAttribute("user");

    boolean checkUserIsNull = user != null;
    if (checkUserIsNull) {
      Object wList = this.getWishListToUser(user);
      Cookie cookieWishList = this.createCokieWishList(wList);
      resp.addCookie(cookieWishList);
    }

    req.getRequestDispatcher("/WEB-INF/views/" + "HomePage.jsp").forward(req, resp);
  }

  private Cookie createCokieWishList(Object wList) throws IOException {
    Gson gson = new Gson();
    return new Cookie("wishlist", URLEncoder.encode(gson.toJson(wList), "UTF8"));
  }

  private ArrayList<WishListModel> getWishListToUser(UserModel user) {
    UserDAO userDao = new UserDAO();
    return userDao.getWishList(user.getId());
  }
}
