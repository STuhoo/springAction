package com.smart.service;

import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //声明为service bean，以便被Spring扫描到后，在其他地方注入
public class UserService {
    private UserDao userDao;
    private LoginLogDao loginLogDao;

    @Autowired //让Spring注入UserDao bean。不注入会怎么样？已经声明了，就应该创建啊！？
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired //让Spring注入LoginLogDao bean。不注入会怎么样？已经声明了，就应该创建啊！？
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

    public boolean hasMatchUser(String userName, String password){
        int matchCount = userDao.getMatchCount(userName, password);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName){
        return userDao.findUserByUserName(userName);
    }

    @Transactional //新概念，完全不懂，需要看书
    public void  loginSuccess(User user){
        user.setCredits(user.getCredits()+5);
        LoginLog loginLog = new LoginLog();
        //user的信息全部来自于User实例，但是User实例的从哪来？？
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user); //因为setCredits进行了更新
        loginLogDao.insertLoginLog(loginLog);
    }

}
