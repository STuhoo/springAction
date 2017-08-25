package com.smart.dao;

/*实现数据库表->domain对象的转化，关键性实现！*/

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository //(1)通过Spring注解方式定义一个DAO bean
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    private final static String UPATE_LOGIN_INFO_SQL = "UPDATE t_user SET " +
            " last_visit=?,last_ip=?,credits=? WHERE user_id=?";
    @Autowired  //(2)自动注入（Spring自动创建、生成可以被使用的对象bean）JdbcTemplate的Bean
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String password){
        String sqlStr =  "SELECT count(*) FROM t_user WHERE user_name=? and password=? ";
        //return jdbcTemplate.queryForInt(sqlStr, new Object[]{userName, password}); //Spring 3.2.2以后没有此函数
        return jdbcTemplate.queryForObject(sqlStr,new Object[]{userName, password}, Integer.class);
    }
    public User findUserByUserName(final String userName){
        String sqlStr = " SELECT user_id,user_name,credits FROM t_user WHERE user_name =? ";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{userName},
                new RowCallbackHandler() { //匿名类实现回调函数，查询返回结果的回调处理
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(resultSet.getInt("credits"));
                    }
                });
        return user;
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPATE_LOGIN_INFO_SQL, new Object[]{user.getLastVisit(),
        user.getLastIp(), user.getCredits(), user.getUserId()});
    }
}
