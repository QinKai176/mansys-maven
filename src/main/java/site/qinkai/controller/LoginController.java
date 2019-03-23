package site.qinkai.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import site.qinkai.pojo.AjaxJson;
import site.qinkai.pojo.User;
import site.qinkai.service.UserService;
import site.qinkai.util.Constants;
import site.qinkai.util.JsonUtil;
import site.qinkai.util.SendMsgUtil;

@Controller
public class LoginController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public AjaxJson login(@RequestBody Map<String, String> map, HttpServletRequest request,
      HttpSession session) throws Exception {
    request.setCharacterEncoding("utf-8");
    String email = map.get(Constants.LOGIN_NAEM);
    String pwd = map.get(Constants.PWD);
    User user = userService.selectUserByName(email);
    if (user == null || !user.getPassword().equals(pwd)) {
      AjaxJson ajaxJson = AjaxJson.returnAjaxJson(false, "用户名或密码错误");
      return ajaxJson;
    } else {
      AjaxJson ajaxJson = AjaxJson.returnAjaxJson(true, "登陆成功");
      session.setAttribute("user", user);
      return ajaxJson;
    }
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public Map<String, String> logout(HttpSession session) throws Exception {
    session.removeAttribute("user");
    return JsonUtil.okMsg();
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public void register(@RequestBody Map<String, String> map, HttpServletRequest request,
      HttpSession session) throws Exception {
    System.out.println("here-------");
    request.setCharacterEncoding("utf-8");
    String username = null;
    String pwd = null;
    String phoneNum = null;
    if (map.containsKey("username") && map.containsKey("password")) {
      username = map.get("username");
      pwd = map.get("password");
      phoneNum = map.get("phoneNum");
    }
    User user = new User();
    user.setUsername(username);
    user.setPassword(pwd);
    user.setPhone_num(phoneNum);
    userService.insertUser(user);
  }

  @RequestMapping(value = "/user/exist", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public User isExist(HttpServletRequest request) throws Exception {
    String username = request.getParameter("username");
    User user = userService.selectUserByName(username);
    if (user == null) {
      return null;
    } else {
      return user;
    }
  }

  @RequestMapping(value = "/user/current", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public User getCurrentUser(HttpSession session) {
    User loginer = (User) session.getAttribute("user");
    return loginer;
  }

  @RequestMapping(value = "/phoneLogin", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public AjaxJson phoneLogin(@RequestBody Map<String, String> map, HttpServletRequest request,
      HttpSession session)
      throws Exception {
    request.setCharacterEncoding("utf-8");
    String phoneNumber = map.get("phone");
    String code = map.get("code");
    System.out.println(phoneNumber + " " + code);
    System.out.println(session.getAttribute("phone") + "," + session.getAttribute("code"));
    if (phoneNumber.equals(session.getAttribute("phone")) && code
        .equals(session.getAttribute("code"))) {
      User loginer = userService.selectUserByPhoneNumber(phoneNumber);
      if (loginer == null) {
        loginer = new User();
        loginer.setUsername(phoneNumber);
        loginer.setPassword("000000");
        loginer.setPhone_num(phoneNumber);
        userService.insertUser(loginer);
      }
      session.removeAttribute("code");
      session.setAttribute("user", loginer);
      return AjaxJson.returnAjaxJson(true, "登陆成功");
    }
    return AjaxJson.returnAjaxJson(false, "手机号或验证码错误");
  }


  @RequestMapping(value = "/phoneRegister", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public AjaxJson phoneRegister(@RequestBody Map<String, String> map, HttpServletRequest request,
      HttpSession session)
      throws Exception {
    request.setCharacterEncoding("utf-8");
    String phoneNumber = map.get("phone");
    String code = map.get("code");
    User loginer = userService.selectUserByPhoneNumber(phoneNumber);
    System.out.println(loginer);
    if (loginer != null) {
      return AjaxJson.returnAjaxJson(false, "用户已注册");
    } else {
      if (phoneNumber == session.getAttribute("phone") && code
          == session.getAttribute("code")) {
        loginer = new User();
        loginer.setUsername(phoneNumber);
        loginer.setPassword("000000");
        loginer.setPhone_num(phoneNumber);
        userService.insertUser(loginer);
        return AjaxJson.returnAjaxJson(true, "登陆成功");
      } else {
        return AjaxJson.returnAjaxJson(false, "验证码错误");
      }
    }
  }

  @RequestMapping(value = "/getcode", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public AjaxJson getCode(@RequestBody Map<String, String> map, HttpServletRequest request) {
    HttpSession session = request.getSession();
    String phone = map.get("phone");
    session.setAttribute("phone", phone);
    AjaxJson ajaxJson = new AjaxJson();
    System.out.println(phone);
    String code = null;
    try {
      code = SendMsgUtil.sendMsg(phone);
      session.setAttribute("code", code);
      session.setMaxInactiveInterval(5 * 60);
    } catch (Exception e) {
      return AjaxJson.returnAjaxJson(true, "验证码发送失败");
    }
    return AjaxJson.returnAjaxJson(true, "注册成功");
  }
}
