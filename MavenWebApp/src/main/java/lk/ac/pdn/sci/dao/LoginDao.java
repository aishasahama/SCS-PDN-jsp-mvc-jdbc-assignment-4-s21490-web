package lk.ac.pdn.sci.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {
	
	public String validateUser(String email, String password) {
		
		String studentId = null;
		
		try {
			Connection con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/university_db",
	                "root",
	                "*web9537"
	            );
			
			PreparedStatement ps = con.prepareStatement("SELECT * FROM students WHERE email=? AND password=?");
			ps.setString(1,email);
			ps.setString(2,password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
				studentId = rs.getString("student_id");
				
			}
			con.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return studentId;
	}

}
