package cn.yyn.web.interceptor;

import cn.yyn.model.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台拦截器：对/admin开头的地址进行拦截，必须经过验证之后才能够访问
 * @author:yyn
 */
public class BackInterceptor implements HandlerInterceptor {

    private static String username = "yyn";
    private static String password = "123456";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser != null){
            return true;
        }
        request.setAttribute("msg", "请先完成登录后操");
        request.getRequestDispatcher("/index.html").forward(request, response);
        return false;

//        boolean flag = true;
////        System.out.println("进入成功！");
//        User user = (User) request.getSession().getAttribute("user");
//        if (null == user) {
//            // 如果用户为空则跳转到error页面
//            // 修改sendRedirect方法为getRequestDispatcher，
//            // 目的是保证地址栏不改变（与前台错误页面响应一致），这样用户就不知道后台页面的地址
//            request.getRequestDispatcher(request.getContextPath() + "/error.html").forward(request, response);
////            response.sendRedirect(request.getContextPath() + "/error.html");
//            flag = false;
//        } else {
//            // 对用户账号进行验证,是否正确
//            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
////                System.out.println("访问后台API");
//                flag = true;
//            } else {
//                flag = false;
//            }
//        }
//        return flag;
    }
}
