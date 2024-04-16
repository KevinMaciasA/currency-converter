package env;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

abstract public class Env {
  public static String read() {
    Properties props = new Properties();
    try {
      FileInputStream input = new FileInputStream(".env");
      props.load(input);
      input.close();
      return props.getProperty("EXCHANGE_API_KEY");
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
