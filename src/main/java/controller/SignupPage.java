package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;

@WebServlet(urlPatterns = "/signup")
public class SignupPage extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/views/" + "SignUpPage.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String url = "";

    String fullname = req.getParameter("fullname");
    String username = req.getParameter("username");
    String password = req.getParameter("password");

    try {
      this.handleSignup(fullname, username, password);

      url = "login";
      resp.sendRedirect(url);
    } catch (Exception err) {
      req.setAttribute("message", err.getMessage());

      url = "/WEB-INF/views/" + "SignUpPage.jsp";
      req.getRequestDispatcher(url).forward(req, resp);
    }
  }

  private void handleSignup(String fullname, String username, String password) throws Exception {
    try {
      UserDAO userDao = new UserDAO();
      userDao.register(fullname, username, password);
    } catch (Exception err) {
      throw err;
    }
  }
}
