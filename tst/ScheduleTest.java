import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by Chris on 12/1/16.
 * This class was created to test methods from the Schedule class
 */
public class ScheduleTest {
    @Test
    public void constructorTest() {
        Schedule s = new Schedule();
        List<Course> c = s.getAllCourses();
        Assert.assertTrue(c != null);
        Assert.assertEquals(0, c.size());
    }

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

    @Test
    public void addTestNegative() {
        Time t1 = new Time(1, 0, true);
        Set<Weekday> d1 = EnumSet.of(Weekday.WEDNESDAY);
        Course c1 = new Course("MAT 245", 1, d1, t1, 30);
        Schedule sa = new Schedule();
        sa.add(c1);
        Collection<Course> allC = sa.getAllCourses();
        try {
            sa.add(c1);
            Assert.fail();
        } catch (ScheduleConflictException e) {
        }
    }

    @Test
    public void getCourseTest() {
        Schedule s = new Schedule();
        Set<Weekday> days = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Set<Weekday> days2 = EnumSet.of(Weekday.TUESDAY, Weekday.THURSDAY);
        Time t1 = new Time(8,15,false);
        Time t2 = new Time(2,0,true);
        Course c1 = new Course("MAT 255", 4, days, t1, 60);
        Course c2 = new Course("ENG 123", 3, days2, t2, 90);
        s.add(c1);
        s.add(c2);
        Course c3 = s.getCourse(Weekday.WEDNESDAY, new Time(8,15,false));
        Course c4 = s.getCourse(Weekday.THURSDAY, new Time(2,0,true));
        Assert.assertTrue(c3.equals(c1));
        Assert.assertTrue(c4.equals(c2));
    }

    @Test
    public void removeTest() {
        Schedule s = new Schedule();
        Set<Weekday> days = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Set<Weekday> days2 = EnumSet.of(Weekday.TUESDAY, Weekday.THURSDAY);
        Time t1 = new Time(8,15,false);
        Time t2 = new Time(2,0,true);
        Course c1 = new Course("MAT 255", 4, days, t1, 60);
        s.add(c1);
        s.remove(Weekday.WEDNESDAY, new Time(8,15,false));
        Course c2 = new Course("MAT 255", 4, days, t1, 60);
        Assert.assertFalse(s.getAllCourses().contains(c1));
    }

    @Test
    public void totalCreditsTest() {
        Schedule s = new Schedule();
        Set<Weekday> days = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Set<Weekday> days2 = EnumSet.of(Weekday.TUESDAY, Weekday.THURSDAY);
        Time t1 = new Time(8,15,false);
        Time t2 = new Time(2,0,true);
        Course c1 = new Course("MAT 255", 4, days, t1, 60);
        Course c2 = new Course("ENG 123", 3, days2, t2, 90);
        s.add(c1);
        s.add(c2);
        Assert.assertEquals(7, s.totalCredits());
    }

    @Test
    public void cloneTest() throws ScheduleConflictException, CloneNotSupportedException {
        Time t1 = new Time(8,15,false);
        Comparator<Course> c = new CourseTimeComparator();
        Schedule s = new Schedule();
        Set<Weekday> days = EnumSet.of(Weekday.TUESDAY, Weekday.THURSDAY);
        Time t2 = new Time(8,15,false);
        Course course = new Course("MAT 255", 4, days, t2, 60);
        s.add(course);
        Schedule s2 = s.clone();
        Course c1 = s.getCourse(Weekday.TUESDAY, t1);
        Course c2 = s2.getCourse(Weekday.TUESDAY, t1);
        Assert.assertEquals(0, c.compare(c1,c2));
    }
}