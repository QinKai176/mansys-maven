package site.qinkai.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import org.json.JSONObject;

public class SendMsgUtil {

  // 请根据我们的说明文档适时调整 url
  final static String url = "https://yun.tim.qq.com/v5/tlssmssvr/sendsms?sdkappid=1400081122&random=";
  static Random random = new Random();
  static int sdkappid = 1400081122;
  static String appkey = "b601cfefffe9fe7921e49061258a73ae";
  static int tplId = 104838;

  public static String sendMsg(String phone) {
    Random random = new Random();
    String rnd = "";
    for (int i = 0; i < 6; i++) {
      rnd += random.nextInt(10);
    }
    String wholeUrl = url + rnd;
    try {
      URL object = new URL(wholeUrl);
      HttpURLConnection con = (HttpURLConnection) object.openConnection();
      // 请求头
      con.setDoOutput(true);
      con.setDoInput(true);
      con.setRequestProperty("Content-Type", "application/json");
      con.setRequestProperty("Accept", "application/json");
      con.setRequestMethod("POST");
      // 请求体
      long time = System.currentTimeMillis() / 1000;
      JSONObject data = new JSONObject();
      JSONObject tel = new JSONObject();
      data.put("ext", "");
      data.put("extend", "");
      String[] params = {rnd};
      data.put("params", params);

      String sig = SHA256Util
          .getSHA256StrJava(
              "appkey=" + appkey + "&random=" + rnd + "&time=" + time + "&mobile=17625906310");
      System.out.println(sig);
      data.put("sig", sig);
      data.put("sign", "qinkai");
      tel.put("nationcode", "86");
      tel.put("mobile", phone);
      data.put("tel", tel);
      data.put("time", time);
      data.put("tpl_id", tplId);

      OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream(), "utf-8");
      wr.write(data.toString());
      wr.flush();
      // 显示 POST 请求返回的内容
      StringBuilder sb = new StringBuilder();
      int HttpResult = con.getResponseCode();
      if (HttpResult == HttpURLConnection.HTTP_OK) {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(con.getInputStream(), "utf-8"));
        String line = null;
        while ((line = br.readLine()) != null) {
          sb.append(line + "\n");
        }
        br.close();
        System.out.println("" + sb.toString());
      } else {
        System.out.println(con.getResponseMessage());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "" + rnd;
  }
}
