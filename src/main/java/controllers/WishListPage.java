package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.UTF8;

@WebServlet(urlPatterns = "/wishlist")
public class WishListPage extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UTF8.set(req, resp);

    req.getRequestDispatcher("/WEB-INF/views/" + "WishListPage.jsp").forward(req, resp);
  }
}
