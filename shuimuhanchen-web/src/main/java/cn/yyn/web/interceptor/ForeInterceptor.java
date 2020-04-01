package cn.yyn.web.interceptor;

import cn.yyn.common.BrowserUtil;
import cn.yyn.model.entity.SysLog;
import cn.yyn.model.entity.SysView;
import cn.yyn.service.SysService;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 前台拦截器：对访问数量进行统计
 * @author:yyn
 */
public class ForeInterceptor implements HandlerInterceptor {

    @Resource
    private SysService sysService;

    private SysLog sysLog = new SysLog();
    private SysView sysView = new SysView();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        //访问者的IP
        String ip = request.getRemoteAddr();
        //访问地址 取前16位 mysql  varchar(20)
        String url = request.getRequestURL().toString().substring(0,16);
        //用户的浏览器名
        String userbrowser = BrowserUtil.getOsAndBrowserInfo(request);

        sysLog.setIp(StringUtils.isEmpty(ip) ? "0.0.0.0" : ip);
        sysLog.setOperateBy(StringUtils.isEmpty(userbrowser) ? "unkown" : userbrowser.substring(0,16));
        sysLog.setOperateUrl(StringUtils.isEmpty(url) ? "unkown" : url);

        // 增加访问量
        sysView.setIp(StringUtils.isEmpty(ip) ? "0.0.0.0" : ip);
        sysService.addView(sysView);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 保存日志信息
            sysLog.setRemark(method.getName());
            sysService.addLog(sysLog);
        } else {
            String uri = request.getRequestURI();
            sysLog.setRemark(uri);
            sysService.addLog(sysLog);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
