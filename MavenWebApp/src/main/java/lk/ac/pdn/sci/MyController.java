package lk.ac.pdn.sci;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lk.ac.pdn.sci.dao.*;


@Controller
public class MyController {
	
	 //Log in page
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String validateLogin(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model,
			HttpSession session
			) {
		 
		
		LoginDao dao = new LoginDao();
		
		String studentId = dao.validateUser(email, password);
		
		if(studentId !=  null) {
		
	        session.setAttribute("studentId", studentId);

			return "redirect:/courses";
		}else {
			model.addAttribute("error","invalid credentials!!");
			return "login";
		}
		
	}
	
	
	//courses page
	@GetMapping("/courses")
	public String showCourses(Model model) {
		CourseDao dao = new CourseDao();

	    model.addAttribute("courses", dao.getAllCourses());
		return "courses";
	}
	
	//register course page
	@PostMapping("/register")
	public String registerCourse(
	        @RequestParam("courseId") String courseId,
	        HttpSession session,
	        Model model
	) {

	    String studentId = (String) session.getAttribute("studentId");

	    if (studentId == null) {
	        return "redirect:/login";
	    }

	    StudentDao sdao = new StudentDao();
	    CourseDao cdao = new CourseDao();

	    //check students existence
	    if (!sdao.checkUser(studentId)) {
	        model.addAttribute("error", "Student not found!");
	        return "courses";
	    }
	    
	    if (!cdao.checkCourse(courseId)) {
	        model.addAttribute("error", "Course not found!");
	        return "courses";
	    }
	    
	    cdao.register(studentId, courseId);

	    model.addAttribute("message", "Registration Successful!");
	    return "success";

	    
	}
	
	
	
}
