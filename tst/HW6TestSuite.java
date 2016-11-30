import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Chris on 11/29/16.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CourseInstructorTest.class,
        CourseTest.class,
        TimeInstructorTest.class,
        TimeTest.class,
        WeekdayInstructorTest.class,
        WeekdayTest.class
})
public class HW6TestSuite {
}
