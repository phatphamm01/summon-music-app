package controller.test;

public class TestNull {
  private String name;

  public TestNull(String name) {
    this.name = name;
  }

  public TestNull() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isNull() {
    return this == null;
  }
}