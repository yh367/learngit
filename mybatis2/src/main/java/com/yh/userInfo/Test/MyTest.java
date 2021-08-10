package com.yh.userInfo.Test;


import java.io.IOException;
import java.io.InputStream;
import com.yh.userInfo.bean.User;
import com.yh.userInfo.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class MyTest {
    @Test
    public void test(){
        try {
            // 读取配置文件 mybatis-config.xml
            InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
            // 根据配置文件构建SqlSessionFactory,这个是产生会话的
            SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(config);
            // 通过 SqlSessionFactory 创建 SqlSession
            //SqlSession 它是面对程序员的一个接口，程序员通过该接口对数据库进行增删改查，该接口有一个默认的实现：DefaultSqlSession
            SqlSession ss = ssf.openSession();
            // SqlSession执行映射文件中定义的SQL，并返回映射结果，相当于创建dao接口的代理对像，从而实现dao的操作功能
//            这种类型的方法都是通过反射来实现的，即你传了一个对象的class过去，
//            就可以通过反射方式来生成这个对象，所以这个方法的返回值类型跟你传的参数有关。
            UserMapper userMapper = ss.getMapper(UserMapper.class);
            // 查询id=1的用户
            User user = userMapper.getUserById(2);
            System.out.println(user);
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

