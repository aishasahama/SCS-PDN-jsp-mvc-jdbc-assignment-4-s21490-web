package lk.ac.pdn.sci.dao;

import java.sql.Connection;
import lk.ac.pdn.sci.model.Courses;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
	
	//check course's existence
	public boolean checkCourse(String courseId) {
		
		boolean exists = false;
		
		try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/university_db",
                "root",
                "*web9537"
            );

            String sql = "SELECT * FROM courses WHERE course_id=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, courseId);

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
	
	
	
	//register student to the course
	public void register(String studentId, String courseId) {

        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/university_db",
                "root",
                "*web9537"
            );

            // generate reg_id eq: R_001
            String regId = "R_" + System.currentTimeMillis();

            String sql = "INSERT INTO registrations VALUES (?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, regId);
            ps.setString(2, studentId);
            ps.setString(3, courseId);
            ps.setDate(4, Date.valueOf(LocalDate.now()));

            ps.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



	public List<Courses> getAllCourses() {
		
		List<Courses> list = new ArrayList<>();
		
		try {
	        Connection con = DriverManager.getConnection(
	            "jdbc:mysql://localhost:3306/university_db",
	            "root",
	            "*web9537"
	        );

	        PreparedStatement ps = con.prepareStatement("SELECT * FROM courses");

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Courses c = new Courses();

	            c.setCourseId(rs.getString("course_id"));
	            c.setName(rs.getString("name"));
	            c.setInstructor(rs.getString("instructor"));
	            c.setCredits(rs.getInt("credits"));

	            list.add(c);
	        }

	        con.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return list;
	}

}
