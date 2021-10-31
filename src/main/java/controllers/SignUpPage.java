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
import config.UTF8;

@WebServlet(urlPatterns = "/signup")
public class SignupPage extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UTF8.set(req, resp);

    req.getRequestDispatcher("/WEB-INF/views/" + "SignUpPage.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    UTF8.set(req, resp);

    String url = "";
    this.handleSignup(req, resp, url);

    req.getRequestDispatcher(url).forward(req, resp);
  }

  private void handleSignup(HttpServletRequest req, HttpServletResponse resp, String url)
      throws ServletException, IOException {
    String fullname = req.getParameter("fullname");
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    UserDAO userDao = new UserDAO();

    try {
      userDao.register(fullname, username, password);

      resp.sendRedirect("login");
      return;
    } catch (Exception err) {
      url = "/WEB-INF/views/" + "SignUpPage.jsp";
      req.setAttribute("message", err.getMessage());
    }
  }
}
