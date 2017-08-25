package com.smart.web;

import com.smart.domain.User;
import com.smart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController //MVC, C
public class LoginController {
    private UserService userService;

    @RequestMapping(value = "/index.html")
    public String loginPage(){//URL的Request响应函数，转发到：login.jsp，具体见login.jsp
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html") //与login.jsp中相对应：c:url 标签
    public ModelAndView loginCheck(HttpServletRequest httpServletRequest, LoginCommand loginCommand){
        //LoginCommand 是对URL的参数的解析！
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(),
                loginCommand.getPassword());
        if (!isValidUser){
            return new ModelAndView("login", "error","用户名或密码错误");
            //login.jsp with error msg
        }else{
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(httpServletRequest.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            httpServletRequest.getSession().setAttribute("user", user);
            return new ModelAndView("main"); //main.jsp with user info
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
