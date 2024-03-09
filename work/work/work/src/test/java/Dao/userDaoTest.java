package Dao;

import User.User;
import com.mysql.jdbc.PreparedStatement;
import org.junit.BeforeClass;
import org.junit.Test;
import util.dbutil;

import java.sql.Connection;
import java.sql.ResultSet;

import static org.junit.Assert.*;

public class userDaoTest {
    private static userDao userDao;
    //PersonDao类中关于CRUD的四个方法都是互不相关的
    //所以只需要构造一个PersonDao对象就可以了
    @BeforeClass
    public static void init(){
        userDao = new userDao();
    }
    /**
     * 判断两个对象，所对应的记录，是否相同
     */
    private void comparePersons(User person11, User person22){
        assertEquals(person11.getUsername(), person22.getUsername());
        assertEquals(person11.getPassword(), person22.getPassword());
    }
    /**
     * 验证原则：插入的记录与表中最大ID所对应的记录进行对比，判断两条记录是否相同
     * @see ，数据库单元测试时，不要产生垃圾数据，即最后应该清空测试时向数据库插入的数据
     */
    @Test
    public void add() throws Exception {
        User user = new User();
        user.setUserId("109");
        user.setUsername("109");
        user.setPassword("109");
        dbutil dbutil = new dbutil();
        Connection con = dbutil.getCon();
        Dao.userDao.add(con,user);
    }

    @Test
    public void login() throws Exception {
        User user = new User();
        user.setUserId("101");
        user.setUsername("101");
        user.setPassword("101");
        dbutil dbutil = new dbutil();
        Connection con = dbutil.getCon();
        Dao.userDao.login(con,user);
    }

    @Test
    public void userLook() throws Exception {
        User user = new User();
        user.setUsername("chensi");
        user.setUserId("101");
        user.setUsername("101");
        user.setPassword("101");
        dbutil dbutil = new dbutil();
        Connection con = dbutil.getCon();
        userDao.add(con,user);
        User person2 = new User();
        ResultSet user2 = userDao.userLook(con, user);
//        this.Assert(user, user2);
    }

    @Test
    public void userDelete() throws Exception {
        User user = new User();
        user.setUserId("103");
        user.setUsername("103");
        user.setPassword("103");
        dbutil dbutil = new dbutil();
        Connection con = dbutil.getCon();
//        Dao.userDao.userDelete(con,user);
    }

    private void Assert(User user, User user2)//帮助代码.起到提取代码的作用.
    {
        assertEquals(user.getUserId(), user2.getUserId());
        assertEquals(user.getUsername(), user2.getUsername());
        assertEquals(user.getPassword(), user2.getPassword());

    }
   
}