import java.util.Comparator;

/**
 * Created by Chris on 11/30/16.
 */
public class CourseNameComparator implements Comparator<Course> {
    public CourseNameComparator() {
    }

    public int compare(Course c1, Course c2) {
        return c1.getName().compareTo(c2.getName());
    }
}
