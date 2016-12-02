import org.junit.Assert;
import org.junit.Test;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Chris on 12/1/16.
 */
public class CourseNameComparatorTest {
    @Test
    public void compareTest() {
        CourseNameComparator test = new CourseNameComparator();
        Set<Weekday> days = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Time t1 = new Time(5, 0, true);
        Time t2 = new Time(8, 15, false);
        Course c1 = new Course("EGR 222", 3, days, t1, 60);
        Course c2 = new Course("EGR 222", 4, days, t2, 60);
        Course c3 = new Course("EGR 212", 4, days, t2, 60);
        Course c4 = new Course("EGR 232", 4, days, t2, 60);
        Assert.assertEquals(0, test.compare(c1, c2));
        Assert.assertEquals(-1, test.compare(c3, c2));
        Assert.assertEquals(1, test.compare(c4, c2));
    }
}
