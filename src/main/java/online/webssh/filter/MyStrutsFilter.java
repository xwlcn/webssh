package online.webssh.filter;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import online.webssh.pojos.User;
import online.webssh.service.UserService;
import online.webssh.utils.DeepCopyUtil;
import online.webssh.utils.SpringContextUtil;

public class MyStrutsFilter extends StrutsPrepareAndExecuteFilter{
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        doBeforeProcessing(request);
        super.doFilter(req, res, chain);
    }
    
    private void doBeforeProcessing(ServletRequest request) {
        HttpSession session = ((HttpServletRequest) request).getSession(true);
       //首先检查session，若已经登陆则直接忽略一下代码
       if (session.getAttribute("user") != null) {
           return;
        }
       Cookie[] cookies = ((HttpServletRequest) request).getCookies();
       String email = null;
       String pwd = null;
       if (cookies != null) {
           for (Cookie c : cookies) {
               if (c.getName().equals("email")) {
                    email = c.getValue();
                }
               if (c.getName().equals("pwd")) {
                    pwd = c.getValue();
                }
            }
       }
       //如果cookie为空，就不必自动登录，让用户重新登录一次
       if(email!=null&&pwd!=null){
           UserService userService = (UserService) SpringContextUtil.getBean("userService");
           User u = userService.findOneByHql("from User u where u.email=?", email);
           if(u!=null&&u.getPassword().equals(pwd)){
        	   User user = DeepCopyUtil.deepCopy(u);
               session.setAttribute("user", user);
               //update the lastLoginTime and lastLoginIP
               u.setLastLoginIP(request.getRemoteAddr());
               u.setLastLoginTime(new Timestamp(new Date().getTime()));
               userService.saveOrUpdate(u);
           }
       }
    }     
}
