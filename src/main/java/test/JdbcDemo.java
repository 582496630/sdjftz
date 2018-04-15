package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.youotech.entity.UserInfo;

public class JdbcDemo {

	public static UserInfo findUserById(Integer userId){
		String sql = "select ID,USERNAME from SDTZ_SYS_USER where ID=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;//定义存放查询结果的结果集
		UserInfo user=null;
		try{
			conn=DbUtil.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1,userId);
			rs=pstmt.executeQuery();//运行查询操作
			if(rs.next()){				
				user=new UserInfo();
				user.setUserName(rs.getString("USERNAME"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//按顺序进行关闭
			DbUtil.close(rs);
			DbUtil.close(pstmt);
			DbUtil.close((PreparedStatement) conn);
			
		}
		return user;
	}
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserInfo userInfo = findUserById(1);
		System.out.println(userInfo.getUserName());
	}*/

}
