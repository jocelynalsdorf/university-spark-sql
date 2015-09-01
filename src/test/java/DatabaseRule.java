import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/university_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteCourseQuery = "DELETE FROM courses *;";
      String deleteStudentQuery = "DELETE FROM students *;";
      String deleteCoursesStudentsQuery = "DELETE FROM courses_students*;";
      con.createQuery(deleteStudentQuery).executeUpdate();
      con.createQuery(deleteCourseQuery).executeUpdate();
      con.createQuery(deleteCoursesStudentsQuery).executeUpdate();
    }
  }
}
