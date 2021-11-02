package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import model.UserModel;

@WebServlet(urlPatterns = "/login")
public class LoginPage extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/" + "LoginPage.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String url = "";

    String username = req.getParameter("username");
    String password = req.getParameter("password");

    try {
      UserModel user = this.handleLogin(username, password);

      HttpSession session = req.getSession();
      session.setAttribute("user", user);

      url = "home";
      resp.sendRedirect(url);
    } catch (Exception err) {
      req.setAttribute("message", err.getMessage());

      url = "/WEB-INF/views/" + "LoginPage.jsp";
      req.getRequestDispatcher(url).forward(req, resp);
    }
  }

  private UserModel handleLogin(String username, String password) throws Exception {
    try {
      UserDAO userDao = new UserDAO();
      return userDao.login(username, password);
    } catch (Exception err) {
      throw err;
    }
  }
}
