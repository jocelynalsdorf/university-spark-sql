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

  @Test
  public void save_assignsIdToObject() {
    Course myCourse = new Course("Mow the lawn", "1");
    myCourse.save();
    Course savedCourse = Course.all().get(0);
    assertEquals(myCourse.getId(), savedCourse.getId());
  }

  @Test
  public void find_findsCourseInDatabase_true() {
     Course myCourse = new Course("Mow the lawn", "1");
     myCourse.save();
     Course savedCourse = Course.find(myCourse.getId());
     assertTrue(myCourse.equals(savedCourse));
   }

   @Test
    public void update_updateCourseInfo() {
      Course savedCourse = new Course("Khao Man Gai", "100 Anywhere");
      savedCourse.save();
      savedCourse.update("McDonalds", "200");
      assertTrue(Course.all().get(0).getDescription().equals("McDonalds"));
    }

}
