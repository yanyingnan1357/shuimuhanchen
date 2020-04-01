package cn.yyn.web.controller;

import cn.yyn.model.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录相关
 */
@Controller
public class LoginController {

    /**
     * 前台登录操作
     * @return
     */
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && password.equals("123456")){
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        }
        map.put("msg", "用户名或密码不正确！");
        return "login";
    }


    /* 后台登录账号密码 */
    private static String username = "yyn";
    private static String password = "123456";


    /**
     * 后台登录操作
     *
     * @param user
     * @param request
     * @return
     */
    @PostMapping("admin/login")
    public String login(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 对用户账号进行验证,是否正确
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/admin/index.html");
        } else {
            response.sendRedirect(request.getContextPath() + "/yyn/login.html");
        }
        return null;
    }

}
