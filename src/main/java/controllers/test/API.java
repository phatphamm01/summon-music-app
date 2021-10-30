package controllers.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import com.mongodb.client.MongoCollection;

import org.bson.types.ObjectId;

import DAO.UserDAO;
import config.DatabaseConnect;
import models.UserModel;
import models.WishListModel;

import static com.mongodb.client.model.Filters.eq;

public class API {
  private static MongoCollection<UserModel> userCollection = new DatabaseConnect("user").getCollection("user",
      UserModel.class);

  public static void main(String[] args) throws Exception {
    // UserDAO userDao = new UserDAO();
    // UserModel user = userCollection.find().first();

  }
}
