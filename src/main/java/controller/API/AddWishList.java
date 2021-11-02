package controller.API;

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
import model.PostWishModel;
import model.UserModel;
import model.WishListModel;

@WebServlet(urlPatterns = "/wish-list")
public class AddWishList extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    PrintWriter writer = resp.getWriter();
    String id = (String) req.getParameter("id");

    boolean isId = id.length() == 0;
    if (isId) {
      writer.print("Vui lòng đăng nhập");
      writer.close();
      return;
    }

    UserModel user = this.getUserById(id);

    boolean checkUserIsNull = user == null;
    if (checkUserIsNull) {
      writer.print("Người dùng không tồn tại");
      writer.close();
      return;
    }

    boolean checkWishListIsNull = user.getWishList() == null;
    if (checkWishListIsNull) {
      return;
    }

    JSONArray wishList = this.getWishListJson(user.getWishList());
    writer.print(wishList);
    writer.close();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("application/json");
    String body = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);

    Gson g = new Gson();
    PostWishModel p = g.fromJson(body, PostWishModel.class);

    UserDAO userDAO = new UserDAO();

    String id = p.getId();
    String img = p.getImg();
    String link = p.getLink();
    String name = p.getName();

    userDAO.addWishList(new ObjectId(id), new WishListModel(name, img, link));

    PrintWriter writer = resp.getWriter();
    writer.print("Cập nhật thành công");
  }

  private UserModel getUserById(String id) {
    UserDAO userDAO = new UserDAO();
    return userDAO.getUserByID(new ObjectId(id));
  }

  private JSONArray getWishListJson(ArrayList<WishListModel> wList) {
    Gson gson = new Gson();
    String listString = gson.toJson(wList, new TypeToken<ArrayList<WishListModel>>() {
    }.getType());
    return new JSONArray(listString);
  }

}
