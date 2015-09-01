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


  }
}
