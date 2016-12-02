import org.junit.Test;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Chris on 12/1/16.
 */
public class ScheduleConflictExceptionTest {
    @Test (expected = ScheduleConflictException.class)
    public void constructorTest() {
        Schedule s = new Schedule();
        Set<Weekday> days = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Time t1 = new Time(5, 0, true);
        Time t2 = new Time(5, 15, true);
        Course c1 = new Course("EGR 222", 3, days, t1, 60);
        Course c2 = new Course("MAT 245", 4, days, t2, 60);
        s.add(c1);
        s.add(c2);
    }
}