package models;

public class WishListModel {
  private String name;
  private String single;
  private String img;
  private String link;

  public WishListModel() {
  }

  public WishListModel(String name, String img, String link) {
    this.name = name;
    this.img = img;
    this.link = link;
  }

  public WishListModel(String name, String single, String img, String link) {
    this.name = name;
    this.single = single;
    this.img = img;
    this.link = link;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSingle() {
    return single;
  }

  public void setSingle(String single) {
    this.single = single;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }
}
