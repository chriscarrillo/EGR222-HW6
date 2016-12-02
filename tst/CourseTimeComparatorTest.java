import org.junit.Assert;
import org.junit.Test;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Chris on 12/1/16.
 */
public class CourseTimeComparatorTest {
    @Test
    public void compareTest() {
        CourseTimeComparator test = new CourseTimeComparator();
        Set<Weekday> days = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Set<Weekday> days2 = EnumSet.of(Weekday.WEDNESDAY, Weekday.THURSDAY, Weekday.FRIDAY);
        Time t1 = new Time(5, 0, true);
        Time t2 = new Time(5, 0, true);
        Time t3 = new Time(2, 0, true);
        Time t4 = new Time(7, 0, true);
        Course c1 = new Course("EGR 222", 3, days, t1, 60);
        Course c2 = new Course("MAT 245", 4, days2, t2, 60);
        Course c3 = new Course("MAT 255", 4, days, t3, 60);
        Course c4 = new Course("PHY 201", 4, days2, t4, 60);
        Assert.assertEquals(0, test.compare(c1, c2));
        //Assert.assertEquals(-1, test.compare(c3, c2));
        //Assert.assertEquals(1, test.compare(c4, c2));
    }
}
