import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Student {
  private int id;
  private String name;
  private String enrolled;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEnrolled() {
    return enrolled;
  }

  public Student(String name, String enrolled) {
    this.name = name;
    this.enrolled = enrolled;
  }

  public static List<Student> all() {
    String sql = "SELECT id, name, enrolled FROM students";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Student.class);
    }
  }

  @Override
  public boolean equals(Object otherStudent){
    if (!(otherStudent instanceof Student)) {
      return false;
    } else {
      Student newStudent= (Student) otherStudent;
      return this.getName().equals(newStudent.getName()) &&
              this.getEnrolled().equals(newStudent.getEnrolled());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO Students(name, enrolled) VALUES (:name, :enrolled)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("enrolled", this.enrolled)
        .executeUpdate()
        .getKey();
    }
  }

  public static Student find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM students where id=:id";
      Student student = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Student.class);
      return student;
    }
  }
  public void addCourse(Course course) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO courses_students (student_id, course_id) VALUES (:student_id, :course_id)";
    con.createQuery(sql)
      .addParameter("student_id", this.getId())
      .addParameter("course_id", course.getId())
      .executeUpdate();
  }
}

public List<Course> getCourses() {
  try(Connection con = DB.sql2o.open()){
    String sql = "SELECT courses.* FROM students JOIN courses_students ON (students.id = courses_students.student_id) JOIN courses ON (courses_students.course_id = courses.id) where students.id= :student_id;";
       List<Course> courses = con.createQuery(sql)
      .addParameter("student_id", this.getId())
      .executeAndFetch(Course.class);
    return courses;
  }
}

//This method is replaced by the above getCourses
/// public ArrayList<Course> getCourses() {
//   try(Connection con = DB.sql2o.open()){
//     String sql = "SELECT course_id FROM courses_students WHERE student_id = :student_id";
//     List<Integer> courseIds = con.createQuery(sql)
//       .addParameter("student_id", this.getId())
//       .executeAndFetch(Integer.class);
//
//     ArrayList<Course> courses = new ArrayList<Course>();
//
//     for (Integer courseId : courseIds) {
//         String courseQuery = "Select * From courses WHERE id = :courseId";
//         Course course = con.createQuery(courseQuery)
//           .addParameter("courseId", courseId)
//           .executeAndFetchFirst(Course.class);
//         courses.add(course);
//     }
//     return courses;
//   }
//  }
public void delete() {
  try(Connection con = DB.sql2o.open()) {
    String deleteQuery = "DELETE FROM students WHERE id = :id;";
      con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();

    String joinDeleteQuery = "DELETE FROM courses_students WHERE student_id = :studentId";
      con.createQuery(joinDeleteQuery)
        .addParameter("studentId", this.getId())
        .executeUpdate();
      }
  }

    public void update(String name, String enrolled) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "UPDATE students SET (name, enrolled) = (:name, :enrolled) WHERE id = :id";
        con.createQuery(sql)
          .addParameter("name", name)
          .addParameter("enrolled", enrolled)
          .addParameter("id", id)
          .executeUpdate();
      }
    }



}
