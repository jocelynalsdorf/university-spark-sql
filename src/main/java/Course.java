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
//
//   public static Task find(int id) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM tasks where id=:id";
//       Task task = con.createQuery(sql)
//         .addParameter("id", id)
//         .executeAndFetchFirst(Task.class);
//       return task;
//     }
//   }
//
//   public void update(String description) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "UPDATE tasks SET description = :description WHERE id = :id";
//       con.createQuery(sql)
//         .addParameter("description", description)
//         .addParameter("id", id)
//         .executeUpdate();
//     }
//   }
//
//   public void delete() {
//     try(Connection con = DB.sql2o.open()) {
//     String sql = "DELETE FROM tasks WHERE id = :id;";
//       con.createQuery(sql)
//         .addParameter("id", id)
//         .executeUpdate();
//     }
//   }
//   public void addCategory(Category category) {
//   try(Connection con = DB.sql2o.open()) {
//     String sql = "INSERT INTO categories_tasks (category_id, task_id) VALUES (:category_id, :task_id)";
//     con.createQuery(sql)
//       .addParameter("category_id", category.getId())
//       .addParameter("task_id", this.getId())
//       .executeUpdate();
//   }
// }
//
// public ArrayList<Category> getCategories() {
//   try(Connection con = DB.sql2o.open()){
//     String sql = "SELECT category_id FROM categories_tasks WHERE task_id = :task_id";
//     List<Integer> categoryIds = con.createQuery(sql)
//       .addParameter("task_id", this.getId())
//       .executeAndFetch(Integer.class);
//
//     ArrayList<Category> categories = new ArrayList<Category>();
//
//     for (Integer categoryId : categoryIds) {
//         String taskQuery = "Select * From categories WHERE id = :categoryId";
//         Category category = con.createQuery(taskQuery)
//           .addParameter("categoryId", categoryId)
//           .executeAndFetchFirst(Category.class);
//         categories.add(category);
//     }
//     return categories;
//   }
// }
// public void delete() {
//   try(Connection con = DB.sql2o.open()) {
//     String deleteQuery = "DELETE FROM tasks WHERE id = :id;";
//       con.createQuery(deleteQuery)
//         .addParameter("id", id)
//         .executeUpdate();
//
//     String joinDeleteQuery = "DELETE FROM categories_tasks WHERE task_id = :taskId";
//       con.createQuery(joinDeleteQuery)
//         .addParameter("taskId", this.getId())
//         .executeUpdate();
//   }
// }
}
