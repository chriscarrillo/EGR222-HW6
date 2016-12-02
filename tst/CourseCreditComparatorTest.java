import org.junit.Assert;
import org.junit.Test;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Chris on 12/1/16.
 */
public class CourseCreditComparatorTest {
    @Test
    public void compareTest() {
        CourseCreditComparator test = new CourseCreditComparator();
        Set<Weekday> days = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Time t1 = new Time(5, 0, true);
        Time t2 = new Time(8, 15, false);
        Course c1 = new Course("EGR 222", 3, days, t1, 60);
        Course c2 = new Course("MAT 245", 4, days, t2, 60);
        Assert.assertEquals(-1, test.compare(c1, c2));
        Assert.assertEquals(0, test.compare(c1, c1));
        Assert.assertEquals(1, test.compare(c2, c1));
    }
}
