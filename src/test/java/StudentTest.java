import java.util.Arrays;

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

  // @Test
  // public void save_savesIntoDatabase_true() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   assertTrue(Category.all().get(0).equals(myCategory));
  // }
  //
  // @Test
  // public void find_findCategoryInDatabase_true() {
  //   Category myCategory = new Category("Household chores");
  //   myCategory.save();
  //   Category savedCategory = Category.find(myCategory.getId());
  //   assertTrue(myCategory.equals(savedCategory));
  // }
}
