import java.util.Arrays;
import java.util.List;


import org.junit.*;
import static org.junit.Assert.*;

public class StudentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Student.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Student firstStudent = new Student("Teresa", "June 2015");
    Student secondStudent = new Student("Teresa", "June 2015");
    assertTrue(firstStudent.equals(secondStudent));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Student myStudent = new Student("Teresa", "June 2015");
    myStudent.save();
    assertTrue(Student.all().get(0).equals(myStudent));
  }

  @Test
  public void find_findStudentInDatabase_true() {
    Student myStudent = new Student("Teresa", "June 2015");
    myStudent.save();
    Student savedStudent = Student.find(myStudent.getId());
    assertTrue(myStudent.equals(savedStudent));
  }

  @Test
   public void add_Course_addsCourseToStudent(){
    Student myStudent = new Student("Teresa", "June 2015");
    myStudent.save();

    Course myCourse = new Course("Math", "123");
    myCourse.save();

    myStudent.addCourse(myCourse);
    Course savedCourse = myStudent.getCourses().get(0);
    assertTrue(myCourse.equals(savedCourse));
  }

  @Test
  public void getCourses_returnsAllCourses_arrayList() {
    Course myCourse = new Course("math", "123");
    myCourse.save();

    Student myStudent = new Student("teresa", "fall 2015");
    myStudent.save();

    myStudent.addCourse(myCourse);
    List savedCourses = myStudent.getCourses();
    assertEquals(savedCourses.size(), 1);
  }

  @Test
  public void delete_deletesAllStudentsAndLists() {
    Course myCourse = new Course("math", "133");
    myCourse.save();

    Student myStudent = new Student("teresa", "june 2015");
    myStudent.save();

    myStudent.addCourse(myCourse);
    myStudent.delete();
    assertEquals(myStudent.getCourses().size(), 0);
  }


  @Test
   public void update_updateStudentInfo() {
     Student savedStudent = new Student("Khao Man Gai", "100 Anywhere");
     savedStudent.save();
     savedStudent.update("McDonalds", "200");
     assertTrue(Student.all().get(0).getName().equals("McDonalds"));
   }

}//end of test class
