package site.qinkai.util;

public class ApplicationHelper {

  public static int StringToInt(String paramStr) {
    int paramInt = 0;
    try {
      paramInt = Integer.parseInt(paramStr);
    } catch (Exception e) {

    }
    return paramInt;
  }

  public static String getFilePath() {
    return System.getenv("FILEPATH");
  }
}
