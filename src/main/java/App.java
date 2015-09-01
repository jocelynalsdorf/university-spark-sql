import java.util.HashMap;
import java.util.List;

import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/courses", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Course> courses = Course.all();
      model.put("courses", courses);
      model.put("template", "templates/courses.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/students", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<Student> students = Student.all();
      model.put("students", students);
      model.put("template", "templates/students.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/courses/:id", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Course course = Course.find(id);
      model.put("course", course);
      model.put("allStudents", Student.all());
      model.put("template","templates/course.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/students/:id", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Student student = Student.find(id);
      model.put("student", student);
      model.put("allCourses", Course.all());
      model.put("template","templates/student.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/students", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       String name = request.queryParams("name");
       String enrolled = request.queryParams("enrolled");
       Student newStudent = new Student(name, enrolled);
       newStudent.save();
       response.redirect("/students");
       return null;
     });

     post("/courses", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       String description = request.queryParams("description");
       String course_number = request.queryParams("course_number");
       Course newCourse = new Course(description, course_number);
       newCourse.save();
       response.redirect("/courses");
       return null;
     });

     post("/add_courses", (request, response) -> {
      int courseId = Integer.parseInt(request.queryParams("course_id"));
      int studentId = Integer.parseInt(request.queryParams("student_id"));
      Student student = Student.find(studentId);
      Course course = Course.find(courseId);
      student.addCourse(course);
      response.redirect("/students/" + studentId);
      return null;
    });

    post("/add_students", (request, response) -> {
      int courseId = Integer.parseInt(request.queryParams("course_id"));
      int studentId = Integer.parseInt(request.queryParams("student_id"));
      Student student = Student.find(studentId);
      Course course = Course.find(courseId);
      course.addStudent(student);
      response.redirect("/courses/" + courseId);
      return null;
    });


  }
}
