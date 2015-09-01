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
//
//   public static Category find(int id) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT * FROM Categories where id=:id";
//       Category Category = con.createQuery(sql)
//         .addParameter("id", id)
//         .executeAndFetchFirst(Category.class);
//       return Category;
//     }
//   }
//   public void addTask(Task task) {
//   try(Connection con = DB.sql2o.open()) {
//     String sql = "INSERT INTO categories_tasks (category_id, task_id) VALUES (:category_id, :task_id)";
//     con.createQuery(sql)
//       .addParameter("category_id", this.getId())
//       .addParameter("task_id", task.getId())
//       .executeUpdate();
//   }
// }
// public ArrayList<Task> getTasks() {
//   try(Connection con = DB.sql2o.open()){
//     String sql = "SELECT task_id FROM categories_tasks WHERE category_id = :category_id";
//     List<Integer> taskIds = con.createQuery(sql)
//       .addParameter("category_id", this.getId())
//       .executeAndFetch(Integer.class);
//
//     ArrayList<Task> tasks = new ArrayList<Task>();
//
//     for (Integer taskId : taskIds) {
//         String taskQuery = "Select * From tasks WHERE id = :taskId";
//         Task task = con.createQuery(taskQuery)
//           .addParameter("taskId", taskId)
//           .executeAndFetchFirst(Task.class);
//         tasks.add(task);
//     }
//     return tasks;
//   }
// }
// public void delete() {
//   try(Connection con = DB.sql2o.open()) {
//     String deleteQuery = "DELETE FROM categories WHERE id = :id;";
//       con.createQuery(deleteQuery)
//         .addParameter("id", id)
//         .executeUpdate();
//
//     String joinDeleteQuery = "DELETE FROM categories_tasks WHERE category_id = :categoryId";
//       con.createQuery(joinDeleteQuery)
//         .addParameter("categoryId", this.getId())
//         .executeUpdate();
//   }
}
