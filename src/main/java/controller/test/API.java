package controller.test;

import org.bson.Document;

public class API {

  public static void main(String[] args) throws Exception {
    TestNull test = (TestNull) new Document().put("name", "pro");

    System.out.println(test);
    if (test.isNull()) {
      System.out.println("null");
    }
  }
}
