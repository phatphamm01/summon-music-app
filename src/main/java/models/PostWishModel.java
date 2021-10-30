package models;

public class PostWishModel {
  private String id;
  private String name;
  private String single;
  private String img;
  private String link;

  public PostWishModel() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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
