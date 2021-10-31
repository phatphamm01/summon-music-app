package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.UserModel;

@WebFilter(urlPatterns = { "/wishlist" })
public class Auth implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse resp = (HttpServletResponse) response;

    if (!this.checkLogin(request, response, chain)) {
      resp.sendRedirect("home");
      return;
    }

    chain.doFilter(request, response);
  }

  private boolean checkLogin(ServletRequest request, ServletResponse response, FilterChain chain) {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpSession session = req.getSession();
    UserModel user = (UserModel) session.getAttribute("user");

    return user != null;
  }
}
