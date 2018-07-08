package site.qinkai.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

  public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
      Exception arg3)
      throws Exception {

  }

  public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2,
      ModelAndView arg3)
      throws Exception {
  }

  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2)
      throws Exception {
    // 先获得session对象
    HttpSession session = req.getSession();
    // 进行session验证
    Object obj = session.getAttribute("user");
    if (obj == null) {
      // 没有登录,重定向到登录页面
      res.sendRedirect("");
      // 不再向后调用
      return false;
    }
    // 登录过
    return true;
  }

}
