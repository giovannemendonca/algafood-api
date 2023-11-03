package com.algaworks.algafood.util;

public class ResourceUtils {

  public static String getContentFromResource(String resourceName) {
    try {
      var inputStream = ResourceUtils.class.getResourceAsStream(resourceName);
      return new String(inputStream.readAllBytes());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
