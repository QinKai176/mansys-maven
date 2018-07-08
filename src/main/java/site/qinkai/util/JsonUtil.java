package site.qinkai.util;

import java.util.HashMap;
import java.util.Map;

public class JsonUtil {

  public static Map<String, String> errorMsg() {
    Map<String, String> map = new HashMap<String, String>();
    map.put("error", "400");
    return map;
  }

  public static Map<String, String> okMsg() {
    Map<String, String> map = new HashMap<String, String>();
    map.put("ok", "200");
    return map;
  }
}
