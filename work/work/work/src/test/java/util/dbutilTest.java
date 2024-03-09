package util;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

public class dbutilTest {
    @Test
    public void testGetConnection() {
        Connection conn = dbutil.getCon();
        assertNotNull(conn); //只要conn不为空，说明数据库连接成功
    }
}