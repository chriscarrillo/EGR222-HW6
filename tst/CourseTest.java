import org.junit.Assert;
import org.junit.Test;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Chris on 11/29/16.
 */
public class CourseTest {
    private void constructorNegativeHelper(String name, int credits, Set<Weekday> days, Time startTime, int duration, String error) {
        try {
            Course c = new Course(name, credits, days, startTime, duration);
            Assert.fail(error);
        } catch(IllegalArgumentException e) {
        }
    }

    @Test
    public void constructorTest() {
        Time startTime = new Time(1, 15, true);
        Set<Weekday> days = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Course phy201 = new Course("PHY 201", 4, days, startTime, 120);
        Assert.assertEquals("PHY 201", phy201.getName());
        Assert.assertEquals(4, phy201.getCredits());
        Assert.assertTrue(startTime.equals(phy201.getStartTime()));
        Assert.assertEquals(120, phy201.getDuration());
    }

    @Test
    public void constructorNegativeTest() {
        String name = "PHY 201";
        Time startTime = new Time(1, 15, true);
        Set<Weekday> days = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);

        constructorNegativeHelper("", 4, days, startTime, 120, "Cannot have an empty course name!");
        constructorNegativeHelper(null, 4, days, startTime, 120, "Cannot have a null course name!");
        constructorNegativeHelper("PHY201", 4, days, startTime, 120, "Cannot have a space in the course name!");
        constructorNegativeHelper(name, 0, days, startTime, 120, "Credits cannot be 0!");
        constructorNegativeHelper(name, 10, days, startTime, 120, "Credits cannot exceed 5!");
        constructorNegativeHelper(name, -7, days, startTime, 120, "Credits cannot be negative!");
        constructorNegativeHelper(name, 4, null, startTime, 120, "Days cannot be null!");
        constructorNegativeHelper(name, 4, days, null, 120, "StartTime cannot be null!");
        constructorNegativeHelper(name, 4, days, startTime, 0, "Duration cannot be 0!");
    }

    @Test
    public void startTimeTest() {
        Time startTime = new Time(1, 15, true);
        Set<Weekday> days = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Course phy201 = new Course("PHY 201", 4, days, startTime, 120);
        Assert.assertTrue(Time.fromString("01:15 PM").equals(phy201.getStartTime()));
        startTime.shift(60);
        Assert.assertTrue(Time.fromString("02:15 PM").equals(startTime));
        Assert.assertFalse(startTime.equals(phy201.getStartTime()));
        Assert.assertTrue(Time.fromString("01:15 PM").equals(phy201.getStartTime()));
    }

    @Test
    public void endTimeTest() {
        Set<Weekday> d = new HashSet<Weekday>();
        d.add(Weekday.MONDAY);
        d.add(Weekday.WEDNESDAY);
        d.add(Weekday.FRIDAY);

        Time t = new Time(01, 15, true);
        Course c = new Course("PHY 201", 4, d, t, 120);

        Time test1 = new Time(03, 15, true);
        Assert.assertEquals(test1, c.getEndTime());

        Time t2 = new Time(01, 15, false);
        Course c2 = new Course("PHY 201", 4, d, t2, 120);

        Time test2 = new Time(03, 15, false);
        Assert.assertEquals(test2, c2.getEndTime());
    }

    @Test
    public void equalsTest() {
        Time t1 = new Time(12, 30, true);
        Time t2 = new Time(12, 30, false);

        Set<Weekday> d = new HashSet<>();
        d.add(Weekday.MONDAY);
        d.add(Weekday.WEDNESDAY);
        d.add(Weekday.FRIDAY);

        Course c1 = new Course("EGR 222", 3, d, t1, 50);
        Assert.assertTrue(c1.equals(c1));

        Course c2 = new Course("EGR 222", 3, d, t2, 50);
        Assert.assertFalse(c1.equals(c2));

        Course c3 = new Course("EGR 225", 3, d, t1, 50);
        Assert.assertFalse(c1.equals(c3));

        Course c4 = new Course("EGR 222", 3, d, t1, 50);
        Assert.assertTrue(c1.equals(c4));

        Course c5 = new Course("EGR 222", 2, d, t1, 30);
        Assert.assertFalse(c1.equals(c5));
    }

    @Test
    public void containsTest() {
        Time t1 = new Time(12, 30, true);
        Time t2 = new Time(12, 30, false);
        Time t3 = new Time(10, 30, true);
        Time t4 = new Time(12, 40, true);
        Time t5 = new Time(11, 19, true);

        Set<Weekday> d = new HashSet<>();
        d.add(Weekday.MONDAY);
        d.add(Weekday.WEDNESDAY);
        d.add(Weekday.FRIDAY);

        Course c1 = new Course("EGR 222", 3, d, t1, 50);

        Assert.assertTrue(c1.contains(Weekday.MONDAY, t1));
        Assert.assertTrue(c1.contains(Weekday.WEDNESDAY, t1));
        Assert.assertTrue(c1.contains(Weekday.FRIDAY, t1));
        Assert.assertFalse(c1.contains(Weekday.TUESDAY, t1));
        Assert.assertFalse(c1.contains(Weekday.THURSDAY, t1));
        Assert.assertFalse(c1.contains(Weekday.MONDAY, t3));
        Assert.assertTrue(c1.contains(Weekday.MONDAY, t4));

        Course c2 = new Course("MAT 255", 3, d, t3, 50);

        Assert.assertFalse(c2.contains(Weekday.MONDAY, t2));
        Assert.assertTrue(c2.contains(Weekday.MONDAY, t3));
        Assert.assertTrue(c2.contains(Weekday.MONDAY, t5));
    }

    @Test
    public void conflictsWithTest() {
        Time t1 = new Time(8, 15, false);
        Time t2 = new Time(9, 15, false);

        Set<Weekday> d1 = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Set<Weekday> d2 = EnumSet.of(Weekday.TUESDAY, Weekday.THURSDAY);

        Course c1 = new Course("MAT 245", 4, d1, t1, 60);
        Assert.assertTrue(c1.conflictsWith(c1));
        Course c2 = new Course("MAT 245", 4, d2, t2, 90);
        Assert.assertFalse(c1.conflictsWith(c2));
    }

    @Test
    public void hashCodeTest() {
        Time startTime = new Time(1, 15, true);
        Set<Weekday> days = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);
        Course courseOne = new Course("PHY 201", 4, days, startTime, 120);
        Course courseTwo = new Course("PHY 201", 4, days, startTime, 120);

        Set<Course> courses = new HashSet<>();
        courses.add(courseOne);
        Assert.assertTrue(courses.contains(courseOne));
        Assert.assertTrue(courses.contains(courseTwo));
        Assert.assertEquals(courseOne.hashCode(), courseTwo.hashCode());
    }
}