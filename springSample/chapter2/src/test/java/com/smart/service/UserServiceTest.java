package com.smart.service;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue; //自动加了static，why？--> 合并成 org.testng.Assert.*即可！

@ContextConfiguration("classpath*:/smart-context.xml") //用于指定配置文件！
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    @Autowired //注入UserService
    private UserService userService;

    @Test
    public void testHasMatchUser(){ //测试用例的写法！！
        boolean b1 = userService.hasMatchUser("admin","123456");
        boolean b2 = userService.hasMatchUser("admin","1111");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void testFindUserByUserName() throws Exception { //why throws Exception?
        for (int i=0; i<100; i++){
            User user = userService.findUserByUserName("admin");
            assertEquals(user.getUserName(),"admin");
        }
    }

    @Test
    public void testAddLoginLog()
    {
        User user = userService.findUserByUserName("admin");
        user.setUserId(1);
        user.setUserName("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }
}
