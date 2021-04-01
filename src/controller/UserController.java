package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.microsoft.sqlserver.jdbc.SQLServerConnection;

import model.User;
import util.DBUtil;

public class UserController {
	
	static Logger log = Logger.getLogger(UserController.class.getName());  
	
	public static void createUser(User user) throws SQLException {
		SQLServerConnection conn = (SQLServerConnection) DBUtil.getConnection("crudServlet");
		//
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Users(name, email, country) VALUES(?,?,?)");
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getCountry());
		
		int count = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
	}
	
	public static int update(User user) throws SQLException {
		SQLServerConnection conn = (SQLServerConnection) DBUtil.getConnection("crudServlet");
		//
		PreparedStatement pstmt 
		= conn.prepareStatement("UPDATE Users SET name = ?, email = ?, country = ? WHERE id = ?");
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getEmail());
		pstmt.setString(3, user.getCountry());
		pstmt.setInt(4, user.getId());
		
		int count = pstmt.executeUpdate();
		pstmt.close();
		conn.close();
	
		return count;
	}
	
	public static User getUserById(int id) throws SQLException {
		SQLServerConnection conn = (SQLServerConnection) DBUtil.getConnection("crudServlet");
		//
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Users WHERE id = ?");
		pstmt.setInt(1, id);
		ResultSet rs = pstmt.executeQuery();
		User user = null;
		
		if(rs.next()) {
		     user = new User( rs.getInt(1),
					  rs.getString(2),
					  rs.getString(3),
					  rs.getString(4));
		}
		pstmt.close();
		conn.close();
		
		return user;
	}
	
	public static List<User> getAllUsers() throws SQLException {
		List<User> list =  new ArrayList<User>();
		
		SQLServerConnection conn = (SQLServerConnection) DBUtil.getConnection("crudServlet");
		// Get Data from DB
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT id, name, email, country FROM Users");
		while(rs.next()) {
			User user = new User( rs.getInt(1),
								  rs.getString(2),
								  rs.getString(3),
								  rs.getString(4));
			list.add(user);
		}
		
		stmt.close();
		conn.close();
		
		return list;
	}
}
