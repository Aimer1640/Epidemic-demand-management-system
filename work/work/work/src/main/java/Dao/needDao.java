package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import User.Need;
import util.stringutil;

public class needDao {

    //检查需求是否存在,若存在，返回0，否则返回1
    public ResultSet needCheck(Connection con, String id) throws Exception {
        //发布需求前 检查needId是否已经存在，若存在，返回0
        String sqlid = "select * from need where needId = ?";
        PreparedStatement pstmtid = (PreparedStatement) con.prepareStatement(sqlid);
        pstmtid.setString(1, id);
        ResultSet rs = pstmtid.executeQuery();
        return rs;
    }

    /// 查询指定内容需求
    public ResultSet list(Connection con, Need needMessage) throws Exception {
        StringBuffer sb = new StringBuffer("select * from need where needId = needId ");

        //按需求ID模糊查询
        if (stringutil.isNotEmpty(needMessage.getNeedId())) {
            sb.append(" and need.needId like '%" + needMessage.getNeedId() + "%'");
        }
        //按用户名模糊查询
        if (stringutil.isNotEmpty(needMessage.getUserName())) {
            sb.append(" and need.userName like '%" + needMessage.getUserName() + "%'");
        }
        //按需求物品模糊查询
        if (stringutil.isNotEmpty(needMessage.getNeedThing())) {
            sb.append(" and need.needThing like '%" + needMessage.getNeedThing() + "%'");
        }
//        if(stringutil.isNotEmpty(needMessage.getNeedThing())) {
//            sb.append(" and need.grade like '%" + needMessage.getGrade() + "%'");
//        }

        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    // 删除需求
    public int delete(Connection con, String id) throws Exception {
        String sql = "delete from need where needId = ?";
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();
    }
    //发布需求
    public int add(Connection con, Need need) throws Exception {
        //发布需求前 检查needId是否已经存在，若存在，返回0
        String sqlid = "select * from need where needId = ?";
        PreparedStatement pstmtid = (PreparedStatement) con.prepareStatement(sqlid);
        pstmtid.setString(1, need.getNeedId());
        ResultSet rs = pstmtid.executeQuery();
        if (rs.next()) {
            return 0;
        }

        String sql = "insert into need values(?,?,?,?,?)";
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);

        pstmt.setString(1, need.getNeedId());
        pstmt.setString(2, need.getUserName());
        pstmt.setString(3, need.getTel());
        pstmt.setString(4, need.getNeedThing());
        pstmt.setString(5, need.getGrade());
        return pstmt.executeUpdate();
    }

    //修改需求
    public int updateims(Connection con, Need needIms) throws Exception {

        String sql = "update need set userName=?, tel=?,needThing=?,grade=? where needId=?";
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);

        pstmt.setString(1, needIms.getNeedId());
        pstmt.setString(2, needIms.getUserName());
        pstmt.setString(3, needIms.getTel());
        pstmt.setString(4, needIms.getNeedThing());
        pstmt.setString(5, needIms.getGrade());
        return pstmt.executeUpdate();

    }

}