package lk.ac.pdn.sci.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentDao {
	
	public void saveStudent(String id ,String name, String email, String password) {
		try {
			Connection con = DriverManager.getConnection(
	                "jdbc:mysql://localhost:3306/university_db",
	                "root",
	                "*web9537"
	            );
			
			PreparedStatement ps = con.prepareStatement("INSERT INTO students VALUES (?,?,?,?)");
			ps.setString(1,id);
			ps.setString(2,name);
			ps.setString(3,email);
			ps.setString(4,password);
			
			ps.executeUpdate();
			con.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	// check if student exists
    public boolean checkUser(String studentId) {

        boolean exists = false;

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/university_db",
                "root",
                "*web9537"
            );

            String sql = "SELECT * FROM students WHERE student_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, studentId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
	
	
	
}
