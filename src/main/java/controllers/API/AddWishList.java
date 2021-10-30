package controllers.API;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.bson.types.ObjectId;
import org.json.JSONArray;

import DAO.UserDAO;
import models.PostWishModel;
import models.UserModel;
import models.WishListModel;

@WebServlet(urlPatterns = "/wish-list")
public class AddWishList extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("application/text");

    PrintWriter writer = resp.getWriter();
    String id = (String) req.getParameter("id");

    System.out.println(id);

    if (id.length() == 0) {
      writer.print("Vui lòng đăng nhập");
      writer.close();
      return;
    }

    UserDAO userDAO = new UserDAO();
    UserModel user = userDAO.getUserByID(new ObjectId(id));

    if (user == null) {
      writer.print("Người dùng không tồn tại");
      writer.close();
      return;
    }

    if (user.getWishList() != null) {
      resp.setContentType("application/json");
      Gson gson = new Gson();
      String listString = gson.toJson(user.getWishList(), new TypeToken<ArrayList<WishListModel>>() {
      }.getType());
      JSONArray jsonArray = new JSONArray(listString);

      System.out.println(jsonArray);
      writer.print(jsonArray);
    }

    writer.close();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setCharacterEncoding("UTF-8");
    req.setCharacterEncoding("UTF-8");

    String body = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
    System.out.println(body);

    Gson g = new Gson();
    PostWishModel p = g.fromJson(body, PostWishModel.class);

    System.out.println("id:" + p.getId());
    System.out.println("img:" + p.getImg());

    UserDAO userDAO = new UserDAO();

    String id = p.getId();
    String img = p.getImg();
    String link = p.getLink();
    String name = p.getName();

    System.out.println("id: " + id);
    System.out.println("img: " + img);
    System.out.println("link: " + link);
    System.out.println("name: " + name);

    userDAO.addWishList(new ObjectId(id), new WishListModel(name, img, link));

    resp.setContentType("application/text");
    PrintWriter writer = resp.getWriter();
    writer.print("Cập nhật thành công");
  }
}
