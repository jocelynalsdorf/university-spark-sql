import java.util.List;
import org.sql2o.*;
import java.util.ArrayList;

public class Course {
  private int id;
  private String description;
  private String course_number;

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public String getCourse_number() {
    return course_number;
  }

  public Course(String description, String course_number) {
    this.description = description;
    this.course_number = course_number;

  }


  @Override
  public boolean equals(Object otherCourse){
    if (!(otherCourse instanceof Course)) {
      return false;
    } else {
      Course newCourse = (Course) otherCourse;
      return this.getDescription().equals(newCourse.getDescription()) &&
             this.getCourse_number().equals(newCourse.getCourse_number()) &&
             this.getId() == newCourse.getId();
    }
  }


  public static List<Course> all() {
    String sql = "SELECT id, description, course_number FROM courses";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Course.class);
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO courses (description, course_number) VALUES (:description, :course_number)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("description", description)
        .addParameter("course_number", course_number)
        .executeUpdate()
        .getKey();
    }
  }

  public static Course find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM courses where id=:id";
      Course course = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(Course.class);
      return course;
    }
  }
//
  public void update(String description, String course_number) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE courses SET (description, course_number) = (:description, :course_number) WHERE id = :id";
      con.createQuery(sql)
        .addParameter("description", description)
        .addParameter("course_number", course_number)
        .addParameter("id", id)
        .executeUpdate();
    }
  }
  //
  // public void delete() {
  //   try(Connection con = DB.sql2o.open()) {
  //   String sql = "DELETE FROM courses WHERE id = :id;";
  //     con.createQuery(sql)
  //       .addParameter("id", id)
  //       .executeUpdate();
  //   }
  // }
  public void addStudent(Student student) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO courses_students (student_id, course_id) VALUES (:student_id, :course_id)";
    con.createQuery(sql)
      .addParameter("student_id", student.getId())
      .addParameter("course_id", this.getId())
      .executeUpdate();
  }
}

public ArrayList<Student> getStudents() {
  try(Connection con = DB.sql2o.open()){
    String sql = "SELECT student_id FROM courses_students WHERE course_id = :course_id";
    List<Integer> studentIds = con.createQuery(sql)
      .addParameter("course_id", this.getId())
      .executeAndFetch(Integer.class);

    ArrayList<Student> students = new ArrayList<Student>();

    for (Integer studentId : studentIds) {
        String courseQuery = "Select * From students WHERE id = :studentId";
        Student student = con.createQuery(courseQuery)
          .addParameter("studentId", studentId)
          .executeAndFetchFirst(Student.class);
        students.add(student);
    }
    return students;
  }
}
public void delete() {
  try(Connection con = DB.sql2o.open()) {
    String deleteQuery = "DELETE FROM courses WHERE id = :id;";
      con.createQuery(deleteQuery)
        .addParameter("id", id)
        .executeUpdate();

    String joinDeleteQuery = "DELETE FROM courses_students WHERE course_id = :courseId";
      con.createQuery(joinDeleteQuery)
        .addParameter("courseId", this.getId())
        .executeUpdate();
  }
}
}
