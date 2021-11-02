package model;

import java.util.ArrayList;

import org.bson.types.ObjectId;

public class UserModel {
  private ObjectId id;
  private String fullName;
  private String username;
  private String password;
  private ArrayList<WishListModel> wishList;

  public UserModel() {
  }

  public UserModel(String fullName, String username, String password) {
    this.fullName = fullName;
    this.username = username;
    this.password = password;
  }

  public UserModel(String fullName, String username, String password, ArrayList<WishListModel> wishList) {
    this.fullName = fullName;
    this.username = username;
    this.password = password;
    this.wishList = wishList;
  }

  public ObjectId getId() {
    return id;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ArrayList<WishListModel> getWishList() {
    return wishList;
  }

  public void setWishList(ArrayList<WishListModel> wishList) {
    this.wishList = wishList;
  }

  public Integer getCountWishList() {
    if (this.wishList == null) {
      return 0;
    }
    return this.wishList.size();
  }
}
