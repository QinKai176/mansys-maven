package site.qinkai.pojo;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/**
 * $.ajax后需要接受的JSON
 *
 * @author
 */
public class AjaxJson {

  private boolean success = true;// 是否成功
  private String msg = "操作成功";// 提示信息
  private Object obj = null;// 其他信息
  private Map<String, Object> attributes = new HashMap<String, Object>();// 其他参数

  public static AjaxJson returnAjaxJson(boolean flag, String msg) {
    AjaxJson ajaxJson = new AjaxJson();
    ajaxJson.setSuccess(flag);
    ajaxJson.setMsg(msg);
    return ajaxJson;
  }

  public Map<String, Object> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, Object> attributes) {
    this.attributes = attributes;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public Object getObj() {
    return obj;
  }

  public void setObj(Object obj) {
    this.obj = obj;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getJsonStr() {
    JSONObject obj = new JSONObject();
    obj.put("success", this.isSuccess());
    obj.put("msg", this.getMsg());
    obj.put("obj", this.obj);
    obj.put("attributes", this.attributes);
    return obj.toString();
  }

}

