// import org.junit.*;
// import static org.junit.Assert.*;
// import org.junit.Rule;
//
//
// public class CourseTest {
//
//   @Rule
//   public DatabaseRule database = new DatabaseRule();
//
//   @Test
//   public void all_emptyAtFirst() {
//     assertEquals(Task.all().size(), 0);
//   }

  // @Test
  // public void equals_returnsTrueIfDescriptionsAretheSame() {
  //   Task firstTask = new Task("Mow the lawn");
  //   Task secondTask = new Task("Mow the lawn");
  //   assertTrue(firstTask.equals(secondTask));
  // }
  //
  // @Test
  // public void save_savesObjectIntoDatabase() {
  //   Task myTask = new Task("Mow the lawn");
  //   myTask.save();
  //   Task savedTask = Task.all().get(0);
  //   assertTrue(savedTask.equals(myTask));
  // }
  //
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
//}
