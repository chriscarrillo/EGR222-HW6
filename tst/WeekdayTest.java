import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Chris on 11/15/16.
 */
public class WeekdayTest {
    @Test
    public void toStringTest() {
        Weekday monday = Weekday.MONDAY;
        Weekday tuesday = Weekday.TUESDAY;
        Weekday wednesday = Weekday.WEDNESDAY;
        Weekday thursday = Weekday.THURSDAY;
        Weekday friday = Weekday.FRIDAY;
        Assert.assertEquals("Monday", monday.toString());
        Assert.assertEquals("Tuesday", tuesday.toString());
        Assert.assertEquals("Wednesday", wednesday.toString());
        Assert.assertEquals("Thursday", thursday.toString());
        Assert.assertEquals("Friday", friday.toString());
    }

    @Test
    public void toShortNameTest() {
        Weekday monday = Weekday.MONDAY;
        Weekday thurs = Weekday.THURSDAY;
        Weekday fri = Weekday.FRIDAY;

        Assert.assertEquals("M", monday.toShortName());
        Assert.assertEquals("R", thurs.toShortName());
        Assert.assertEquals("F", fri.toShortName());
    }

    @Test
    public void fromStringTest() {
        Weekday mon = Weekday.fromString("MoNdAy");
        Assert.assertEquals(Weekday.MONDAY, mon);
        Weekday thurs = Weekday.fromString("R");
        Assert.assertEquals(Weekday.THURSDAY, thurs);
    }

    @Test (expected = IllegalArgumentException.class)
    public void fromStringNegativeTest() {
        Weekday test = Weekday.fromString("");
        Weekday test2 = Weekday.fromString("BoYs");
        Assert.assertEquals(Weekday.FRIDAY, test);
        Assert.assertEquals(Weekday.WEDNESDAY, test2);
    }
}