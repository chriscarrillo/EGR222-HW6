import java.util.Comparator;

/**
 * Created by Chris on 11/30/16.
 */
public class CourseTimeComparator implements Comparator<Course> {
    public CourseTimeComparator() {
    }

    public int compare(Course c1, Course c2) {
        Time timeOne = c1.getStartTime();
        Time timeTwo = c2.getStartTime();

        if (!timeOne.equals(timeTwo))
            return timeOne.compareTo(timeTwo);
        else if (c1.getDuration() != c2.getDuration())
            return c1.getDuration() - c2.getDuration();

        return c1.getStartTime().compareTo(c2.getStartTime());
    }
}
