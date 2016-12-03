import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Chris on 12/1/16.
 */
public class ScheduleTest {
    @Test
    public void addTest() {
        Time t1 = new Time(1, 0, true);
        Set<Weekday> d1 = EnumSet.of(Weekday.WEDNESDAY);
        Course c1 = new Course("MAT 245", 1, d1, t1, 30);
        Schedule sa = new Schedule();
        sa.add(c1);
        Collection<Course> allC = sa.getAllCourses();
        Assert.assertTrue(allC.contains(c1));

        Time t2 = new Time(8, 30, false);
        Set<Weekday> d2 = EnumSet.of(Weekday.MONDAY);
        Course c2 = new Course("EGR 182", 3, d2, t2, 60);
        sa.add(c2);
        Assert.assertTrue(allC.contains(c2));
    }
}
