import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Rule;


public class CourseTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Course.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Course firstCourse = new Course("Mow the lawn", "1");
    Course secondCourse = new Course("Mow the lawn", "1");
    assertTrue(firstCourse.equals(secondCourse));
  }

  @Test
  public void save_savesObjectIntoDatabase() {
    Course myCourse = new Course("Mow the lawn", "1");
    myCourse.save();
    Course savedCourse = Course.all().get(0);
    assertTrue(savedCourse.equals(myCourse));
  }

  // @Test
  // public void save_assignsIdToObject() {
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   Task savedTask = Task.all().get(0);
  //   assertEquals(myTask.getId(), savedTask.getId());
  // }
  //
  // @Test
  // public void find_findsTaskInDatabase_true() {
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   Task savedTask = Task.find(myTask.getId());
  //   assertTrue(myTask.equals(savedTask));
  // }
}
